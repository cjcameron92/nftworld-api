package org.amirion.nftworld.listener;

import org.amirion.nftworld.NftWorldPlugin;
import org.amirion.nftworld.contract.PolygonWRLDToken;
import org.amirion.nftworld.network.Network;
import org.jetbrains.annotations.NotNull;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Hash;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.utils.Convert;

import java.util.ArrayList;
import java.util.List;

public class PolygonListener {

    private static final String TRANSFER_EVENT_TOPIC = Hash.sha3String("Transfer(address,address,uint256)");
    private static final String TRANSFER_REF_EVENT_TOPIC = Hash.sha3String("TransferRef(address,address,uint256,uint256)");


    public PolygonListener(@NotNull NftWorldPlugin plugin) {
        EthFilter transferFilter = new EthFilter(
                DefaultBlockParameterName.LATEST,
                DefaultBlockParameterName.LATEST,
                plugin.getContractProps().getPolygonPlayerContract()
        ).addOptionalTopics(TRANSFER_EVENT_TOPIC, TRANSFER_REF_EVENT_TOPIC);

        Network.POLYGON.getProperties().getWeb3j().ethLogFlowable(transferFilter).subscribe(log -> {
            final String hash = log.getTopics().get(0);

            if (hash.equals(TRANSFER_EVENT_TOPIC)) handleTransferEvent(log);
            if (hash.equals(TRANSFER_REF_EVENT_TOPIC)) handleTransferRefEvent(log);

        }, Throwable::printStackTrace);
    }

    public void handleTransferEvent(@NotNull Log log) {

    }

    public void handleTransferRefEvent(@NotNull Log log) {
        final List<String> topics = new ArrayList<>(log.getTopics());
        final List<Type> data = FunctionReturnDecoder.decode(log.getData(), PolygonWRLDToken.TRANSFERREF_EVENT.getNonIndexedParameters());
        final TypeReference<Address> typeReference = new TypeReference<>() {};

        final Address from = (Address) FunctionReturnDecoder.decodeIndexedValue(topics.get(1), typeReference);
        final Address to = (Address) FunctionReturnDecoder.decodeIndexedValue(topics.get(2), typeReference);

        final Uint256 amount = (Uint256) data.get(0);
        final Uint256 ref = (Uint256) data.get(1);

        final double receieved = Convert.fromWei(amount.getValue().toString(), Convert.Unit.ETHER).doubleValue();



    }
}
