package com.crypto.app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WalletTest {

    private Wallet wallet = new Wallet();

    @Test
    public void parsedCSV() {
        wallet.loadFile("src/main/resources/wallet.csv");
        assertTrue(
                wallet.getWalletPrices() != null
                        && wallet.getWalletQuantity() != null
                        && wallet.getWalletSymbols() != null);
    }
}
