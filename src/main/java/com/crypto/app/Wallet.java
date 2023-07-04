package com.crypto.app;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class Wallet {

    // Class variables
    private String[] walletSymbols;

    private double[] walletPrices;

    private double[] walletQuantity;

    private double[] currentPrices;

    private float[] upliftValue;

    public List<String[]> walletValues;

    public List<String[]> getWalletValues() {
        return walletValues;
    }

    public double[] getWalletPrices() {
        return walletPrices;
    }

    public void setWalletPrices(double[] walletPrices) {
        this.walletPrices = walletPrices;
    }

    public double[] getWalletQuantity() {
        return walletQuantity;
    }

    public void setWalletQuantity(double[] walletQuantity) {
        this.walletQuantity = walletQuantity;
    }

    public double[] getCurrentPrices() {
        return currentPrices;
    }

    public void setCurrentPrices(double[] currentPrices) {
        this.currentPrices = currentPrices;
    }

    public void setWalletValues(List<String[]> walletValues) {
        this.walletValues = walletValues;
    }

    // Sea of getters and setters
    public String[] getWalletSymbols() {
        return walletSymbols;
    }

    public void setWalletSymbols(String[] walletSymbols) {
        this.walletSymbols = walletSymbols;
    }

    public float[] getUpliftValue() {
        return upliftValue;
    }

    public void setUpliftValue(float[] upliftValue) {
        this.upliftValue = upliftValue;
    }

    // Constructor
    public Wallet(String filePath) {
        setWalletValues(readCSVFile(filePath));
        parseWalletData();
    }

    private void parseWalletData() {
        List<String[]> walletValues = getWalletValues();
        List<String> symbols = new ArrayList<String>();
        List<Double> quantities = new ArrayList<Double>();
        List<Double> prices = new ArrayList<Double>();

        if (walletValues != null) {
            for (String[] value : walletValues) {
                symbols.add(value[0]);
                quantities.add(Double.parseDouble(value[1]));
                prices.add(Double.parseDouble(value[2]));
            }
        }
    }

    private List<String[]> readCSVFile(String filePath) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> wallet_values = csvReader.readAll();
            return wallet_values;
        } catch (Exception IOException) {
            String msg = "File not found!\n" + IOException.getMessage();
            System.out.print(msg);
            return null;
        }

    }

}
