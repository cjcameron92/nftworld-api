package org.amirion.nftworld.user;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleUserRegistry implements UserRegistry {

    private final Map<UUID, User> users = new ConcurrentHashMap<>();

    @Override public void register(@NotNull UUID uuid, @NotNull User user) {
        this.users.put(uuid, user);
    }

    @Override public @NotNull Map<UUID, User> getAll() {
        return users;
    }
}
