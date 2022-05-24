package org.amirion.nftworld.network;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class NetworkProp {

    private String httpsRpc;
    private Web3j web3j;

    public NetworkProp() {
        this.httpsRpc = "https://localhost";
        this.web3j = Web3j.build(new HttpService(httpsRpc));
    }

    public void setHttpsRpc(String httpsRpc) {
        this.httpsRpc = httpsRpc;
        this.web3j = Web3j.build(new HttpService(httpsRpc));
    }

    public String getHttpsRpc() {
        return httpsRpc;
    }

    public Web3j getWeb3j() {
        return web3j;
    }
}
