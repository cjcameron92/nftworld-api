package org.amirion.nftworld.user;

import org.amirion.nftworld.Wallets;
import org.amirion.nftworld.wallet.Wallet;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

public class User {

    private final Set<String> walletAddresses;

    public User() {
        this.walletAddresses = new CopyOnWriteArraySet<>();
    }

    public Set<String> getWalletAddresses() {
        return walletAddresses;
    }

    public List<Wallet> getWallets() {
        return walletAddresses.stream().filter(Objects::nonNull).filter(address -> Wallets.getWallet(address).isPresent()).map(address -> Wallets.getWallet(address).get()).collect(Collectors.toList());
    }

    public Optional<Wallet> getPrimaryWallet() {
        return getWalletAddresses().isEmpty() ? Optional.empty() : Optional.of(getWallets().get(0));
    }
}
