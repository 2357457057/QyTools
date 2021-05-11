
package com.qy.Threads;

import com.qy.test.Test;
import com.qy.view.MainView;

public class CvThread extends Thread {
    @Override
    public synchronized void run() {
        System.out.println(3);
        String executeCount = MainView.sendCount.getText();
        String intervalTime = MainView.times.getText();

        try {

            Thread.sleep(5000);

            Test.runVbs("select");
            Test.runVbs("copy");
            Test.runVbs("enter");
            for (int i = 0; i < Integer.parseInt(executeCount); i++) {
                Test.runVbs("paste");
                Thread.sleep(100);
                Test.runVbs("enter");
                Thread.sleep(Integer.parseInt(intervalTime));
            }
        } catch (InterruptedException awtException) {
            awtException.printStackTrace();
        }
    }
}

