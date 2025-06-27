package com.neusoft.neu24.his.neuhospital.entity;

import java.util.ArrayList;
import java.util.List;

public class Dept {
    private static List<String> sex = new ArrayList<>();
    static {
        sex.add("男");
        sex.add("女");
    }
    public static List<String> getSex() {
        return sex;
    }

    //---------------------------------------------
    private static List<String> age = new ArrayList<>();
    static {
        age.add("年");
        age.add("月");
        age.add("日");
    }
    public static List<String> getAge() {
        return age;
    }

    //--------------------------------------
    private static List<String> week = new ArrayList<>();
    static {
        week.add("星期一");
        week.add("星期二");
        week.add("星期三");
        week.add("星期四");
        week.add("星期五");
        week.add("星期六");
        week.add("星期日");
    }
    public static List<String> getWeek() {
        return week;
    }
    //-------------------------------------------------
    public static List<String> day = new ArrayList<>();
    static {
        day.add("上午");
        day.add("下午");
    }
    public static List<String> getDay() {
        return day;
    }
    //--------------------------------------------
    public static List<String> room = new ArrayList<>();
    static {
        room.add("儿科");
        room.add("骨科");
        room.add("脑科");
        room.add("神经科");
    }
    public static List<String> getRoom() {
        return room;
    }
    //-----------------------------
    public static List<String> number = new ArrayList<>();
    static {
        number.add("教授");
        number.add("专家");
        number.add("普通");
    }
    public static List<String> getNumber() {
        return number;
    }
    //-----------------------------
    public static List<String> doctor = new ArrayList<>();
    static {
        doctor.add("刘书涵");
        doctor.add("刘武涵");
        doctor.add("王凯北");
        doctor.add("王凯南");
        doctor.add("刘奕泽");
        doctor.add("刘二泽");
        doctor.add("黄文聪");
        doctor.add("黄文明");
    }
    public static List<String> getDoctor() {
        return doctor;
    }
    //---------------------------------
    public static List<String> pay = new ArrayList<>();
    static {
        pay.add("现金");
        pay.add("医保卡");
    }
    public static List<String> getPay() {
        return pay;
    }


}
