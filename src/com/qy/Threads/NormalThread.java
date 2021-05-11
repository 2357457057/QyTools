
package com.qy.Threads;

import com.qy.test.Test;
import com.qy.view.MainView;


import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;


public class NormalThread extends Thread {
    @Override
    public synchronized void run() {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        String sendTexts = MainView.textArea.getText();
        String executeCount = MainView.sendCount.getText();
        String intervalTime = MainView.times.getText();
        String[] split = sendTexts.split("\n");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int length = split.length;
        int temp = 0;
        for (int i = 0; i < Integer.parseInt(executeCount); i++) {
            if (temp >= length) {
                temp = 0;
            }
            StringSelection tText = new StringSelection(split[temp]);
            clip.setContents(tText, null);
            // Ctrl + v
            Test.runVbs("paste");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Test.runVbs("enter");
            try {
                Thread.sleep(Integer.parseInt(intervalTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            temp++;
        }


    }
}
