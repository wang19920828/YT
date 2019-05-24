package org.fh.general.ecom.common.utils;

public class NumberChanges {
    public static String NumberChanges(String mon) {
        String Month = null;
        switch (mon) {
            case "01":
                Month = "一月";
                break;
            case "02":
                Month = "二月";
                break;
            case "03":
                Month = "三月";
                break;
            case "04":
                Month = "四月";
                break;
            case "05":
                Month = "五月";
                break;
            case "06":
                Month = "六月";
                break;
            case "07":
                Month = "七月";
                break;
            case "08":
                Month = "八月";
                break;
            case "09":
                Month = "九月";
                break;
            case "10":
                Month = "十月";
                break;
            case "11":
                Month = "十一月";
                break;
            case "12":
                Month = "十二月";
                break;
        }
    return Month;
    }

}