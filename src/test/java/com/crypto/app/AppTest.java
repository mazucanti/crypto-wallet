package com.crypto.app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest {
    @Test
    public void anwserCorrect() {
        String expectedMsg = "total=101652.89,best_asset=BCH,best_performance=58.32,worst_asset=XRP,worst_performance=0.00";
        App.main(null);
        String finalMessage = App.getFinal_msg();
        assertTrue(expectedMsg.equals(finalMessage));
    }

}
