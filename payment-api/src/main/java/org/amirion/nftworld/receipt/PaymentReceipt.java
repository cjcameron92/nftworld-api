package org.amirion.nftworld.receipt;

import org.amirion.nftworld.network.Network;
import org.amirion.nftworld.wallet.Wallet;
import org.jetbrains.annotations.NotNull;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

public interface PaymentReceipt {

    @NotNull String getWalletAddress();

    @NotNull Network getNetwork();

    @NotNull BigInteger getAmount();

    @NotNull TransactionReceipt getTransactionReceipt();

    @NotNull CompletableFuture<Boolean> isCompleted();

    long getTimestamp();

    static @NotNull PaymentReceipt create(@NotNull Wallet wallet, @NotNull Network network, @NotNull BigInteger bigInteger, @NotNull TransactionReceipt transactionReceipt) {
        return new SimplePaymentReceipt(wallet.getAddress(), network, bigInteger, transactionReceipt);
    }

    class SimplePaymentReceipt implements PaymentReceipt {

        private final String walletAddress;
        private final Network network;
        private final BigInteger bigInteger;
        private final TransactionReceipt transactionReceipt;
        private final CompletableFuture<Boolean> completableFuture;
        private final long timestamp;


        public SimplePaymentReceipt(String walletAddress, Network network, BigInteger bigInteger, TransactionReceipt transactionReceipt) {
            this.walletAddress = walletAddress;
            this.network = network;
            this.bigInteger = bigInteger;
            this.transactionReceipt = transactionReceipt;
            this.completableFuture = new CompletableFuture<>();
            this.timestamp = System.currentTimeMillis();
        }

        @NotNull @Override public String getWalletAddress() {
            return walletAddress;
        }

        @NotNull @Override public Network getNetwork() {
            return network;
        }

        @Override public @NotNull BigInteger getAmount() {
            return bigInteger;
        }

        @NotNull @Override public TransactionReceipt getTransactionReceipt() {
            return transactionReceipt;
        }

        @Override public long getTimestamp() {
            return timestamp;
        }

        @Override public @NotNull CompletableFuture<Boolean> isCompleted() {
            return completableFuture;
        }
    }

}
