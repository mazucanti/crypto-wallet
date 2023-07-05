package com.crypto.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

public class App {

    public static String final_msg = "total=%.2f,best_asset=%s,best_performance=%.2f,worst_asset=%s,worst_performance=%.2f";

    public static String getFinal_msg() {
        return final_msg;
    }

    public static void setFinal_msg(String final_msg) {
        App.final_msg = final_msg;
    }

    public static void main(String[] args) {
        Wallet wallet = new Wallet();
        Double walletTotal = 0.0;
        Double worstAsset = Double.MAX_VALUE;
        Double bestAsset = Double.MIN_VALUE;
        Integer bestIdx = -1;
        Integer worstIdx = -1;
        String filePath = "src/main/resources/wallet.csv";
        wallet.loadFile(filePath);

        List<Double> currentPrices = runRequests(wallet);
        System.out.println(currentPrices);

        for (int i = 0; i < currentPrices.size(); i++) {
            Double performance = currentPrices.get(i) / wallet.getWalletPrices().get(i);
            walletTotal += currentPrices.get(i) * wallet.getWalletQuantity().get(i);

            if (worstAsset > performance) {
                worstAsset = performance;
                worstIdx = i;
            }
            if (bestAsset < performance) {
                bestAsset = performance;
                bestIdx = i;
            }
        }
        String results = String.format(getFinal_msg(),
                walletTotal,
                wallet.getWalletSymbols().get(bestIdx),
                bestAsset,
                wallet.getWalletSymbols().get(worstIdx),
                worstAsset);
        setFinal_msg(results);
        System.out.println(results);
    }

    private static List<Double> runRequests(Wallet wallet) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<String> symbols = wallet.getWalletSymbols();
        List<Double> currentPrices = new ArrayList<>(symbols.size());
        for (int i = 0; i < symbols.size(); i++) {
            String symbol = symbols.get(i);
            Callable<String> callable = new Comms(symbol);
            try {
                String response = executor.submit(callable).get();
                JSONObject jsonObj = new JSONObject(response);
                Double currentPrice = jsonObj.getJSONArray("data").getJSONObject(0).getDouble("priceUsd");
                currentPrices.add(i, currentPrice);
                String msg = String.format("The price of %s is USD %.2f", symbol, currentPrice);
                System.out.println(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        executor.shutdown();
        return currentPrices;
    }
}
