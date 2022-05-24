package org.amirion.nftworld;

import org.amirion.nftworld.nft.NftFactory;
import org.amirion.nftworld.nft.SimpleNftFactory;

public class Assets {

    private static final NftFactory factory;

    static {
        factory = new SimpleNftFactory();
    }

    public static NftFactory getFactory() {
        return factory;
    }
}
