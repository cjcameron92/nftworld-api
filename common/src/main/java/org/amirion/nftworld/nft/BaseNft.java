package org.amirion.nftworld.nft;

import org.jetbrains.annotations.NotNull;

public interface BaseNft {

    @NotNull String getContractId();

    @NotNull String getTokenId();

    int getBalance();
}
