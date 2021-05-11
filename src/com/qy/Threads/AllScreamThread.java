
package com.qy.Threads;

import com.qy.test.Test;
import com.qy.view.MainView;


import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;


public class AllScreamThread extends Thread {
    @Override
    public synchronized void run() {


        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        String sendTexts = MainView.textArea.getText();
        String executeCount = MainView.sendCount.getText();
        String  intervalTime = MainView.times.getText();


        String[] split = sendTexts.split("\n");
        try {
            Thread.sleep(5000);
            int length = split.length;
            int temp = 0;
            for (int i = 0; i < Integer.parseInt(executeCount); i++) {
                if (temp >= length) {
                    temp = 0;
                }
               StringSelection tText = new StringSelection(split[temp]);
                clip.setContents(tText, null);
                Test.runVbs("enter");
                // Ctrl + v
                Test.runVbs("paste");
                Thread.sleep(100);
                Test.runVbs("enter");
                Thread.sleep(Integer.parseInt(intervalTime));
                temp++;
            }
        } catch (InterruptedException awtException) {
            awtException.printStackTrace();
        }

    }
}

