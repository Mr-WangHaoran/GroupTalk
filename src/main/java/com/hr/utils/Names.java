package com.hr.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 存储所有用户名
 */
public class Names {

    private static List<String> nameList = new CopyOnWriteArrayList<>();

    private static volatile long num = 1;

    public static synchronized boolean exists(String name){
        return nameList.contains(name);
    }

    public synchronized static void addName(String name){
        if(!exists(name)){
            nameList.add(name);
        }
    }

    public static synchronized long generator(){
        return num++;
    }

    public static synchronized String formatDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(date);
    }

    public static void removeName(String name){
        nameList.remove(name);
    }
}
