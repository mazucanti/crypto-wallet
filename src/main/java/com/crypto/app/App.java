package com.crypto.app;
import java.util.List;


public class App {
    public static void main(String[] args) {
        Wallet wallet = new Wallet("src/main/resources/wallet.csv");
        List<String[]> w = wallet.getWalletValues();
        System.out.println("Hello World!");
    }
}
