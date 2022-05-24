package org.amirion.nftworld;

import org.amirion.nftworld.network.Network;
import org.amirion.nftworld.receipt.PaymentReceipt;
import org.amirion.nftworld.wallet.Wallet;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface PaymentHandler {
    
    @NotNull CompletableFuture<Optional<PaymentReceipt>> pay(@NotNull Wallet wallet, @NotNull Network network, @NotNull BigInteger amount);
    
    default @NotNull CompletableFuture<Optional<PaymentReceipt>> pay(@NotNull Wallet  wallet, @NotNull Network network, @NotNull BigDecimal bigDecimal) {
        return pay(wallet, network, bigDecimal.toBigInteger());
    }
    
    default @NotNull CompletableFuture<Optional<PaymentReceipt>> pay(@NotNull Wallet wallet, @NotNull Network network, double amount) {
        return pay(wallet, network, BigDecimal.valueOf(amount));
    }
    
    default @NotNull CompletableFuture<Optional<PaymentReceipt>> pay(@NotNull Wallet wallet, @NotNull BigInteger amount) {
        return pay(wallet, Network.POLYGON, amount);
    }
    
    default @NotNull CompletableFuture<Optional<PaymentReceipt>> pay(@NotNull Wallet wallet, @NotNull BigDecimal bigDecimal) {
        return pay(wallet, Network.POLYGON, bigDecimal);
    }
    
    default @NotNull CompletableFuture<Optional<PaymentReceipt>> pay(@NotNull Wallet wallet, double amount) {
        return pay(wallet, Network.POLYGON, amount);
    }
    
    @NotNull CompletableFuture<PaymentReceipt> request(@NotNull Wallet wallet, @NotNull Network network, @NotNull BigInteger amount, boolean duplicate);

    default @NotNull CompletableFuture<PaymentReceipt> request(@NotNull Wallet wallet, @NotNull Network network, @NotNull BigInteger amount) {
        return request(wallet, network, amount, false);
    }

    default @NotNull CompletableFuture<PaymentReceipt> request(@NotNull Wallet wallet, @NotNull Network network, @NotNull BigDecimal bigDecimal, boolean duplicate) {
        return request(wallet, network, bigDecimal.toBigInteger(), duplicate);
    }

    default @NotNull CompletableFuture<PaymentReceipt> request(@NotNull Wallet wallet, @NotNull Network network, double amount, boolean duplicate) {
        return request(wallet, network, BigDecimal.valueOf(amount), duplicate);
    }

    default @NotNull CompletableFuture<PaymentReceipt> request(@NotNull Wallet wallet, @NotNull Network network, double amount) {
        return request(wallet, network, amount, false);
    }
}
