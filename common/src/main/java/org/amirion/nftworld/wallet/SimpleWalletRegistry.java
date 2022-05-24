package org.amirion.nftworld.wallet;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleWalletRegistry implements WalletRegistry {

    private final Map<String, Wallet> wallets = new LinkedHashMap<>();

    @Override public void register(@NotNull String address, @NotNull Wallet wallet) {
        wallets.put(address, wallet);
    }

    @Override public @NotNull Map<String, Wallet> getAll() {
        return wallets;
    }
}
