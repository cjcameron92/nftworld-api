package org.amirion.nftworld.nft;

import com.google.common.base.Preconditions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.amirion.nftworld.Assets;
import org.jetbrains.annotations.NotNull;

public interface Nft extends BaseNft {

    static @NotNull Nft deserialize(@NotNull JsonElement element) {
        Preconditions.checkArgument(element.isJsonObject());

        final JsonObject object = element.getAsJsonObject();

        Preconditions.checkArgument(object.has("contract"));
        Preconditions.checkArgument(object.has("id"));
        Preconditions.checkArgument(object.has("balance"));

        final String contractAddress = object.get("contract").getAsJsonObject().get("address").getAsString();
        final String tokenId = object.get("id").getAsJsonObject().get("tokenId").getAsString();
        final int balance = object.get("balance").getAsInt();

        return Assets.getFactory().makeNft(contractAddress, tokenId, balance);

    }

}
