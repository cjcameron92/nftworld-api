package org.amirion.nftworld.nft;

import org.jetbrains.annotations.NotNull;

public class SimpleNftFactory implements NftFactory {

    @Override public @NotNull Nft makeNft(@NotNull String contractAddress, @NotNull String tokenId, int balance) {
        return new SimpleNft(tokenId, contractAddress, balance);
    }

    private static final class SimpleNft implements Nft {

        private final String contractId, tokenId;
        private final int balance;

        public SimpleNft(String contractId, String tokenId, int balance) {
            this.contractId = contractId;
            this.tokenId = tokenId;
            this.balance = balance;
        }

        @Override public @NotNull String getContractId() {
            return contractId;
        }

        @Override public @NotNull String getTokenId() {
            return tokenId;
        }

        @Override
        public int getBalance() {
            return balance;
        }
    }
}
