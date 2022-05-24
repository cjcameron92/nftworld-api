package org.amirion.nftworld;


import org.amirion.nftworld.wallet.SimpleWalletRegistry;
import org.amirion.nftworld.wallet.Wallet;
import org.amirion.nftworld.wallet.WalletRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
public class Wallets {

    private static final WalletRegistry registry;

    static {
        registry = new SimpleWalletRegistry();
    }

     public static @NotNull Optional<Wallet> getWallet(@NotNull String address) {
        return registry.getAll().containsKey(address) ? Optional.of(registry.getAll().get(address)) : Optional.empty();
    }


    public static WalletRegistry getRegistry() {
        return registry;
    }
}
