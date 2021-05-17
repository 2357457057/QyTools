package com.qy.tools;


import com.qy.Main;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FileExtensionModify {


    public void singleModify(String filePath, String extension) {
        File file = new File(filePath);
        String name = file.getName();
        String[] split = name.split("\\.");
        //当前文件无拓展名
        if (split.length == 1) {
            //拓展名已填写
            if (extension.trim().length() != 0) {
                Main.GLOBAL_INFO_STATION.appendText("\n" + "[waring] 当前文件无拓展名 现将为其添加拓展名");
                File file1 = new File(filePath + "." + extension.trim());
                boolean b = file.renameTo(file1);
                if (b) {
                    Main.GLOBAL_INFO_STATION.appendText("\n" + "[info] 修改成功 新文件名: " + file1.getName() + " 源文件名: " + file.getName());
                } else {
                    Main.GLOBAL_INFO_STATION.appendText("\n" + "[error] 请重新选择文件 源文件名: " + file.getName());
                }
            } else {
                Main.GLOBAL_INFO_STATION.appendText("\n" + "[waring] 当前文件无拓展名 且 尚未填写拓展名 无修改");
            }
            //文件有拓展名
        } else {
            //拓展名已填写
            if (extension.trim().length() != 0) {
                int length = split[split.length - 1].length();
                String substring = name.substring(0, name.length() - length);

                String s = substring + extension.trim();

                singleModify(file, s);
            } else {
                Main.GLOBAL_INFO_STATION.appendText("\n" + "[waring] 尚未填写拓展名 此操作 将删除拓展名");
                int length = split[split.length - 1].length();

                String s = name.substring(0, name.length() - length - 1);

                singleModify(file, s);
            }
        }
    }

    private void singleModify(File file, String s) {
        File file1 = new File(file.getParent() + "\\" + s);

        boolean b = file.renameTo(file1);
        if (b) {
            Main.GLOBAL_INFO_STATION.appendText("\n" + "[info] 修改成功 新文件名: " + file1.getName() + " 源文件名: " + file.getName());
        } else {
            Main.GLOBAL_INFO_STATION.appendText("\n" + "[error] 请重新选择文件 源文件名: " + file.getName());
        }
    }


    public void currentPathModify(File file, String oldExtension, String newExtension) {


        //判断是否为一个文件夹
        if (file.isDirectory()) {
            //文件过滤器
            FilenameFilter filenameFilter = (dir, name) -> {
                //已填写旧拓展名
                if (oldExtension.trim().length() != 0)
                    if (name.contains("." + oldExtension.trim())) {
                        Main.GLOBAL_INFO_STATION.appendText("\n" + "[info]找到匹配的文件: " + name);
                        return true;
                    } else return false;
                    //当未填写旧拓展名放行全部文件 其中可能包含文件夹
                else {
                    Main.GLOBAL_INFO_STATION.appendText("\n" + "[info]找到匹配的文件:" + name);
                    return true;
                }
            };

            File[] files = file.listFiles(filenameFilter);
            //当返回值不为nul1时
            if (files != null) {
                Main.GLOBAL_INFO_STATION.appendText("n" + "[info]共找到 >" + files.length + "<个匹的文件 ");
               // 当未填写旧拓展名时
                if (oldExtension.trim().length() == 0) {
/*                     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("警告!当前未填写旧拓展名 将全修改当前目录下所有文件!");
                    // 不能直接用show show是非阻基的 showAndWait是阻塞的
                    alert.showAndWait();
                    //警告框客户的选择
                    if (alert.getResult().getText().equals("确定") || alert.getResult().getText().equalsIgnoreCase("ok")) {*/

                        for (File file1 : files) {
                            //判断为非文件夹
                            if (!file1.isDirectory()) {
                                String[] split = file1.getName().split("\\.");
                                String fileName = file1.getName();
                                //文件有拓展名
                                if (split.length != 1) {
                                    //新拓展名未填
                                    if (newExtension.trim().length() == 0) {
                                        Main.GLOBAL_INFO_STATION.appendText("\n[warning] 未填写拓展名 此操作将删除拓展名 ");
                                        int length = split[split.length - 1].length();
                                        String substring = fileName.substring(0, fileName.length() - length - 1);
                                        singleModify(file1, substring);
                                        //新拓展名已
                                    } else {
                                        int length = split[split.length - 1].length();
                                        String substring = fileName.substring(0, fileName.length() - length);
                                        String s = substring + newExtension.trim();
                                        singleModify(file1, s);
                                    }

                                    //文件无拓展名
                                } else {
                                    //新拓展名已填写
                                    if (newExtension.trim().length() != 0) {
                                        Main.GLOBAL_INFO_STATION.appendText("\n[warning] 当前文件无拓展名现将为其添加拓展名");
                                        File file2 = new File(file1.getPath() + "." + newExtension.trim());
                                        boolean b = file1.renameTo(file2);
                                        if (b) {
                                            Main.GLOBAL_INFO_STATION.appendText("\n[info] 修改成功" + "新文件名" + file2.getName() + " 原文件名:" + file1.getName());
                                        } else {
                                            Main.GLOBAL_INFO_STATION.appendText("\n" + "[error] 修改失败 请重新选择文件 原文件名: " + file1.getName());
                                        }
                                    } else {
                                        Main.GLOBAL_INFO_STATION.appendText("\n[warning] 当前文件无拓展名 且 未填写新拓展名 无修改");
                                    }
                                }
                            }
                        }
                  /*  } else {
                        Main.GLOBAL_INFO_STATION.appendText("\n[info] 用户取消修改");
                    }*/

                } else {
                    for (File file1 : files) {
                        //当该文件非文件夹
                        if (!file1.isDirectory()) {
                            String[] split = file1.getName().split("\\.");
                            String fileName = file1.getName();
                            //文件有拓展名
                            if (split.length > 1) {
                                //新拓展名未填
                                if (newExtension.trim().length() == 0) {
                                    Main.GLOBAL_INFO_STATION.appendText("\n[warning] 未填写拓展名此操作将删除拓展名");
                                    int length = split[split.length - 1].length();
                                    String substring = fileName.substring(0, fileName.length() - length - 1);
                                    singleModify(file1, substring);
                                    //新拓展名已填
                                } else {
                                    int length = split[split.length - 1].length();
                                    String substring = fileName.substring(0, fileName.length() - length);
                                    String s = substring + newExtension.trim();
                                    singleModify(file1, s);
                                }
                                //文件无拓展名
                            } else {
                                if (newExtension.trim().length() != 0) {
                                    Main.GLOBAL_INFO_STATION.appendText("\n[warning] 当前文件无拓展名现将为其添加拓展名");
                                    File file2 = new File(file1.getPath() + newExtension.trim());
                                    boolean b = file1.renameTo(file2);
                                    if (b) {
                                        Main.GLOBAL_INFO_STATION.appendText("\n" + "[info]修改成功 新文件名:" + file2.getName() + " 原文件名:" + file1.getName());
                                    } else {
                                        Main.GLOBAL_INFO_STATION.appendText("\n[error] 修改失败请重新选择文件源文件名" + file1.getName());
                                    }
                                } else {
                                    Main.GLOBAL_INFO_STATION.appendText("\n[warning] 当前文件无拓展名 且 未填写拓展名 无修改");
                                }
                            }
                        }
                    }
                }
            } else
                Main.GLOBAL_INFO_STATION.appendText("\n[error] 当前路径无文件");
        } else {
            Main.GLOBAL_INFO_STATION.appendText("\n[error] 当前路径不是一个文件夹，请找一个文件夹");
        }
    }

    public void currentPathRecursiveModify(File filePath, String oldExtension, String newExtension) {
        ArrayList<File> list = new ArrayList<>();
        for (File file : recursiveQuery(filePath, list)) {
            currentPathModify(file, oldExtension, newExtension);
        }


    }

    List<File> recursiveQuery(File fileRoot, List<File> dirList) {
        File[] files = fileRoot.listFiles(File::isDirectory);
        if (files != null)
            dirList.addAll(Arrays.asList(files));
        return dirList;
    }
}
