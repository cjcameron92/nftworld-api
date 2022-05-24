package org.amirion.nftworld;

import org.amirion.nftworld.contract.EthereumWRLDToken;
import org.amirion.nftworld.contract.PolygonWRLDToken;
import org.amirion.nftworld.network.Network;
import org.amirion.nftworld.property.ContractProps;
import org.bukkit.plugin.java.JavaPlugin;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

public class NftWorldPlugin extends JavaPlugin {

    private final ContractProps contractProps = new ContractProps();

    private PolygonWRLDToken polygonToken;
    private EthereumWRLDToken ethereumToken;

    private TransactionManager polygonTransactionManager;

    @Override public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        setupNetwork();
        setupContract();
        setupTransaction();
    }

    @Override public void onDisable() {

    }

    public void setupNetwork() {
        for (Network network : Network.values()) {
            network.getProperties().setHttpsRpc(getConfig().getString(network.name().toLowerCase() + "-rpc"));
        }
    }

    public void setupContract() {
        contractProps.setPolygonPlayerContract(getConfig().getString("contracts.polygon-player"));
        contractProps.setPolygonPlayerContract(getConfig().getString("contracts.polygon-wrld"));
        contractProps.setPolygonPlayerContract(getConfig().getString("contracts.ethereum-wrld"));

    }

    public void setupTransaction() {
        final Credentials credentials = Credentials.create(getConfig().getString("private-server-key"));

        polygonTransactionManager = new FastRawTransactionManager(
                Network.ETHEREUM.getProperties().getWeb3j(),
                credentials,
                137
        );

        ethereumToken = EthereumWRLDToken.load(
                contractProps.getEthereumWrldContract(),
                Network.ETHEREUM.getProperties().getWeb3j(),
                credentials,
                Network.ETHEREUM.getGasProvider()
        );

        polygonToken = PolygonWRLDToken.load(
                contractProps.getPolygonWrldContract(),
                Network.POLYGON.getProperties().getWeb3j(),
                polygonTransactionManager,
                new StaticGasProvider(BigInteger.valueOf(90_100_000_000L), BigInteger.valueOf(9_000_000))
        );

    }

    public ContractProps getContractProps() {
        return contractProps;
    }
}
