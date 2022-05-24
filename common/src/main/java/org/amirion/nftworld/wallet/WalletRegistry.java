package org.amirion.nftworld.wallet;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface WalletRegistry {

    void register(@NotNull String address, @NotNull Wallet wallet);

    @NotNull Map<String, Wallet> getAll();

}
