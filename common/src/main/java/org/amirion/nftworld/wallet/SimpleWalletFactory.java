package org.amirion.nftworld.wallet;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.amirion.nftworld.network.Network;
import org.amirion.nftworld.nft.Nft;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class SimpleWalletFactory implements WalletFactory {

    @Override public @NotNull Wallet makeWallet(@NotNull String address) {
        return new SimpleWallet(address);
    }

    private record SimpleWallet(String address) implements Wallet {

        private SimpleWallet(@NotNull String address) {
            this.address = address;
        }

        @Override public @NotNull String getAddress() {
            return address;
        }

        @Override public @NotNull CompletableFuture<Set<Nft>> getAssets() {
            return CompletableFuture.supplyAsync(() -> {
                final Set<Nft> assets = new HashSet<>();

                for (Network network : Network.values()) {
                    assets.addAll(getNftsFrom(network, true));
                }

                return assets;
            });
        }

        @Override public @NotNull CompletableFuture<Integer> getTotalCount() {
            return CompletableFuture.supplyAsync(() -> {
                int total = 0;

                for (Network network : Network.values()) {
                    total += getTotalCountFrom(network);
                }

                return total;
            });

        }

        public int getTotalCountFrom(Network network) {
            try {
                final String url = network.getProperties().getHttpsRpc() + "/getNFTs?owner=" + address + String.format("&withMetadata=%s", true);
                final JsonElement response = JsonParser.parseString(HttpClient.newHttpClient().send(HttpRequest.newBuilder().uri(URI.create(url)).build(), HttpResponse.BodyHandlers.ofString()).body());
                final JsonObject body = response.getAsJsonObject();

                return body.get("totalCount").getAsInt();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                return 0;
            }
        }

        public Set<Nft> getNftsFrom(@NotNull Network network, boolean metadata) {
            final Set<Nft> assets = new HashSet<>();

            try {
                final String url = network.getProperties().getHttpsRpc() + "/getNFTs?owner=" + address + String.format("&withMetadata=%s", metadata);
                final JsonElement response = JsonParser.parseString(HttpClient.newHttpClient().send(HttpRequest.newBuilder().uri(URI.create(url)).build(), HttpResponse.BodyHandlers.ofString()).body());
                final JsonObject body = response.getAsJsonObject();
                final JsonArray ownedNfts = body.get("ownedNfts").getAsJsonArray();

                for (JsonElement e : ownedNfts) {
                    assets.add(Nft.deserialize(e));
                }

                System.out.println(response.toString());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            return assets;
        }

    }
}
