package org.amirion.nftworld.nft;

import org.jetbrains.annotations.NotNull;

public interface NftFactory {

    @NotNull Nft makeNft(@NotNull String contractAddress, @NotNull String tokenId, int balance);
}
