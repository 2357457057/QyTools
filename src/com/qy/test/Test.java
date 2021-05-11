package com.qy.test;


import java.io.*;

public class Test {
    final static String parentPath;

    static {
        parentPath = new Test().getParentPath();
        try {
            FileWriter  copy = new FileWriter(parentPath + "/copy.vbs");


            copy.write("dim ws\n" +
                    "set ws=wscript.createobject(\"wscript.shell\")\n" +
                    "ws.sendKeys\"^c\"\n" +
                    "wscript.quit");
            copy.flush();
            copy.close();
            FileWriter enter = new FileWriter(parentPath + "/enter.vbs");
            enter.write("dim ws\n" +
                    "set ws=wscript.createobject(\"wscript.shell\")\n" +
                    "ws.sendkeys\"{enter}\"\n" +
                    "wscript.quit");
            enter.flush();
            enter.close();

            FileWriter paste = new FileWriter(parentPath + "/paste.vbs");
            paste.write("dim ws\n" +
                    "set ws=wscript.createobject(\"wscript.shell\")\n" +
                    "ws.sendKeys\"^v\"\n" +
                    "wscript.quit");
            paste.flush();
            paste.close();

            FileWriter select = new FileWriter(parentPath + "/select.vbs");
            select.write("dim ws\n" +
                    "set ws=wscript.createobject(\"wscript.shell\")\n" +
                    "ws.sendKeys\"^a\"\n" +
                    "wscript.quit");
            select.flush();
            select.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void runVbs(String fileName) {
        System.out.println(parentPath);
        String[] Cmd = new String[]{"wscript", parentPath + "/" + fileName + ".vbs"};
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(Cmd);
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getParentPath() {
        String str = new File(new File(this.getClass().getResource("/enter.vbs").getPath()).getParent()).getParent();
        return str.substring(6);
    }

}