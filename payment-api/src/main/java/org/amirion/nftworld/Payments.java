package org.amirion.nftworld;

public class Payments {

  private static final PaymentHandler handler;

  static {
      handler = null;
  }

    public static PaymentHandler getHandler() {
        return handler;
    }
}
