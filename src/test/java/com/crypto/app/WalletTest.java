package com.crypto.app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WalletTest {

    private Wallet wallet = new Wallet("src/main/resources/wallet.csv");

    @Test
    public void parsedCSV() {
        assertTrue(wallet.walletValues != null);
    }
}
