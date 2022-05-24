package org.amirion.nftworld.user;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

public interface UserRegistry {

    void register(@NotNull UUID uuid, @NotNull User user);

    @NotNull Map<UUID, User> getAll();

}
