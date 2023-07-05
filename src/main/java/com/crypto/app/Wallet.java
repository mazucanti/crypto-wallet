package com.crypto.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Wallet {

    // Class variables
    private List<String> walletSymbols;

    private List<Double> walletPrices;

    private List<Double> walletQuantity;

    public List<String> getWalletSymbols() {
        return walletSymbols;
    }

    public void setWalletSymbols(List<String> walletSymbols) {
        this.walletSymbols = walletSymbols;
    }

    public List<Double> getWalletPrices() {
        return walletPrices;
    }

    public void setWalletPrices(List<Double> walletPrices) {
        this.walletPrices = walletPrices;
    }

    public List<Double> getWalletQuantity() {
        return walletQuantity;
    }

    public void setWalletQuantity(List<Double> walletQuantity) {
        this.walletQuantity = walletQuantity;
    }

    public void loadFile(String filePath) {

        System.out.println("Loading csv...");

        String line = "";
        String splitBy = ",";

        List<Double> prices = new ArrayList<>();
        List<Double> quantities = new ArrayList<>();
        List<String> symbols = new ArrayList<>();

        try {
            // parsing a CSV file into BufferedReader class constructor

            BufferedReader br = new BufferedReader(new FileReader(filePath));

            while ((line = br.readLine()) != null) // returns a Boolean value
            {

                if (!(line.contains("symbol") || line.contains("quantity") || line.contains("price"))) {

                    String[] tuple = line.split(splitBy); // use comma as separator

                    symbols.add(tuple[0]);
                    quantities.add(Double.parseDouble(tuple[1]));
                    prices.add(Double.parseDouble(tuple[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        setWalletPrices(prices);
        setWalletQuantity(quantities);
        setWalletSymbols(symbols);
    }

}
