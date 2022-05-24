package org.amirion.nftworld.wallet;

import org.jetbrains.annotations.NotNull;

public interface WalletFactory {

    @NotNull Wallet makeWallet(@NotNull String address);
}
