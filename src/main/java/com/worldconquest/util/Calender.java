package com.worldconquest.util;

import java.util.HashMap;

import com.worldconquest.WorldConquest;

public class Calender {
    int frameNum;
    int day, month, year;
    String date = "";
    int dayLegnth = 5;// in seconds
    WorldConquest wc;
    HashMap<Integer, Integer> monthDayHashMap = new HashMap<>();
    HashMap<Integer, String> monthNameHashMap = new HashMap<>();

    public Calender(int month, int day, int year, WorldConquest wc) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.wc = wc;
        initCalender();
    }

    private void initCalender() {
        monthDayHashMap.put(1, 31);
        monthDayHashMap.put(2, 28);
        monthDayHashMap.put(3, 31);
        monthDayHashMap.put(4, 30);
        monthDayHashMap.put(5, 31);
        monthDayHashMap.put(6, 30);
        monthDayHashMap.put(7, 31);
        monthDayHashMap.put(8, 31);
        monthDayHashMap.put(9, 30);
        monthDayHashMap.put(10, 31);
        monthDayHashMap.put(11, 30);
        monthDayHashMap.put(12, 31);

        monthNameHashMap.put(1, "January");
        monthNameHashMap.put(2, "February");
        monthNameHashMap.put(3, "March");
        monthNameHashMap.put(4, "April");
        monthNameHashMap.put(5, "May");
        monthNameHashMap.put(6, "June");
        monthNameHashMap.put(7, "July");
        monthNameHashMap.put(8, "August");
        monthNameHashMap.put(9, "September");
        monthNameHashMap.put(10, "October");
        monthNameHashMap.put(11, "November");
        monthNameHashMap.put(12, "December");

    }

    public void update() {

        if (frameNum == WorldConquest.FPS * dayLegnth) {
            frameNum = 0;
            dailyUpdate();
        }
        frameNum++;
    }

    private void dailyUpdate() {
        wc.dailyUpdate();
        day++;
        if (day > monthDayHashMap.get(month)) {
            day = 1;
            month++;
        }
        if (month > 12) {
            month = 1;
            year++;
        }

    }

    public String getDate() {
        date = monthNameHashMap.get(month) + " " + day + ", " + year;

        return date;
    }

}
