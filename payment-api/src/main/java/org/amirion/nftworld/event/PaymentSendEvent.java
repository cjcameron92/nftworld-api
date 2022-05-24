package org.amirion.nftworld.event;

import org.amirion.nftworld.receipt.PaymentReceipt;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PaymentSendEvent extends Event {

    private static final HandlerList i = new HandlerList();

    private final PaymentReceipt receipt;

    public PaymentSendEvent(@NotNull PaymentReceipt receipt) {
        this.receipt = receipt;
    }

    public PaymentReceipt getReceipt() {
        return receipt;
    }

    @Override public @NotNull HandlerList getHandlers() {
        return i;
    }

    public static HandlerList getHandlerList() {
        return i;
    }
}
