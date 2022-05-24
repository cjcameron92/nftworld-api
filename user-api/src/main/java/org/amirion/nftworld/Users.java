package org.amirion.nftworld;

import org.amirion.nftworld.user.SimpleUserRegistry;
import org.amirion.nftworld.user.User;
import org.amirion.nftworld.user.UserRegistry;
import org.amirion.nftworld.wallet.Wallet;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class Users {

    private static final UserRegistry registry;

    static {
        registry = new SimpleUserRegistry();
    }

    public static Optional<User> getUser(@NotNull UUID uuid) {
        return registry.getAll().containsKey(uuid) ? Optional.of(registry.getAll().get(uuid)) : Optional.empty();
    }

    public static Optional<Wallet> getUserWallet(@NotNull UUID uuid) {
        return getUser(uuid).isPresent() ? getUser(uuid).flatMap(User::getPrimaryWallet) : Optional.empty();
    }

    public static UserRegistry getRegistry() {
        return registry;
    }
}
