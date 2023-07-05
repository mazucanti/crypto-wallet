package com.crypto.app;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

import org.json.JSONObject;

public class Comms implements Callable {

    private String Symbol;

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getSymbol() {
        return Symbol;
    }

    public Comms(String symbol) {
        setSymbol(symbol);
    }

    @Override
    public String call() throws MalformedURLException, IOException {
        System.out.println(String.format("Making API request for %s", getSymbol()));
        return makeRequest(getSymbol());

    }

    private String makeRequest(String symbol) throws MalformedURLException, IOException {
        String baseUrl = String.format("https://api.coincap.io/v2/assets?search=%s", symbol);
        String json_str = getJson_str(baseUrl);

        JSONObject json_obj = new JSONObject(json_str);
        String id = json_obj.getJSONArray("data").getJSONObject(0).getString("id");

        System.out.println("Checking " + id + " information...");

        String baseUrlId = String.format(
                "https://api.coincap.io/v2/assets/%s/history?interval=d1&start=1617753600000&end=1617753601000", id);

        String response = getJson_str(baseUrlId);
        return response;

    }

    private String getJson_str(String baseUrl) throws MalformedURLException, IOException, ProtocolException {
        URL url = new URL(baseUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("accept", "application/json");

        InputStream responseStream = con.getInputStream();
        String json_str = new String(responseStream.readAllBytes(), StandardCharsets.UTF_8);
        con.disconnect();
        return json_str;
    }
}
