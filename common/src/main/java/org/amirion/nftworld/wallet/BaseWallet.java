package org.amirion.nftworld.wallet;

import org.amirion.nftworld.nft.Nft;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface BaseWallet {

    @NotNull String getAddress();

    @NotNull CompletableFuture<Set<Nft>> getAssets();

    @NotNull CompletableFuture<Integer> getTotalCount();




}
