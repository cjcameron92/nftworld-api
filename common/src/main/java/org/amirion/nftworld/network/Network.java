package org.amirion.nftworld.network;

import org.jetbrains.annotations.NotNull;
import org.web3j.tx.gas.DefaultGasProvider;

public enum Network {

    ETHEREUM(new NetworkProp(), new DefaultGasProvider()),
    POLYGON(new NetworkProp(), new DefaultGasProvider());

    private final NetworkProp properties;
    private final DefaultGasProvider gasProvider;

    Network(@NotNull NetworkProp properties, @NotNull DefaultGasProvider gasProvider) {
        this.properties = properties;
        this.gasProvider = gasProvider;
    }

    public NetworkProp getProperties() {
        return properties;
    }

    public DefaultGasProvider getGasProvider() {
        return gasProvider;
    }
}
