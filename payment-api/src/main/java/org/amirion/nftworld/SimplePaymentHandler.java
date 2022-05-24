package org.amirion.nftworld;

import org.amirion.nftworld.contract.PolygonWRLDToken;
import org.amirion.nftworld.event.PaymentSendEvent;
import org.amirion.nftworld.network.Network;
import org.amirion.nftworld.receipt.PaymentReceipt;
import org.amirion.nftworld.wallet.Wallet;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class SimplePaymentHandler implements PaymentHandler {

    private final PolygonWRLDToken contract;

    public SimplePaymentHandler(@NotNull PolygonWRLDToken contract) {
        this.contract = contract;
    }

    @Override public @NotNull CompletableFuture<Optional<PaymentReceipt>> pay(@NotNull Wallet wallet, @NotNull Network network, @NotNull BigInteger amount) {
        final CompletableFuture<Optional<PaymentReceipt>> completableFuture = new CompletableFuture<>();
        if (network != Network.POLYGON) {
            Bukkit.getLogger().severe("You can only pay using the polygon network.");
            completableFuture.complete(Optional.empty());
            return completableFuture;
        }

        try {
            contract.transfer(wallet.getAddress(), amount).sendAsync().thenAccept(receipt -> {
               final PaymentSendEvent event = new PaymentSendEvent(PaymentReceipt.create(wallet, Network.POLYGON, amount, receipt));
               event.callEvent();
               completableFuture.complete(Optional.of(event.getReceipt()));
            });
        } catch (Exception e) {
            e.printStackTrace();
            completableFuture.complete(Optional.empty());
        }

        return completableFuture;
    }

    @Override public @NotNull CompletableFuture<PaymentReceipt> request(@NotNull Wallet wallet, @NotNull Network network, @NotNull BigInteger amount, boolean duplicate) {
        return null;
    }
}
