package com.nabigeto.gavin.nabigetonabi.EditReminder;

/**
 * Created by Gavin on 7/2/2018.
 */

public class edit_adapter_model {


    public String user_id;
    public String date;
    public String mtime;
    public String reminder_title;

    public int year;
    public int month;
    public int day;

    public int hour;
    public int minute;

    public boolean hourly;
    public boolean daily;
    public boolean weekly;
    public boolean monthly;
    public boolean yearly;

    public boolean set_active;

    public String reminder_content;

    public edit_adapter_model(){};

    public edit_adapter_model(String user_id, String date, String mtime, String reminder_title, int year, int month, int day, int hour, int minute, boolean set_active, boolean hourly, boolean daily, boolean weekly, boolean monthly, boolean yearly, String reminder_content){

        this.user_id = user_id;
        this.date = date;
        this.mtime = mtime;
        this.reminder_title = reminder_title;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.set_active = set_active;
        this.hourly = hourly;
        this.daily = daily;
        this.weekly = weekly;
        this.monthly = monthly;
        this.yearly = yearly;
        this.reminder_content = reminder_content;

    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getReminder_title() {
        return reminder_title;
    }

    public void setReminder_title(String reminder_title) {
        this.reminder_title = reminder_title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public boolean getHourly() {
        return hourly;
    }

    public void setHourly(boolean hourly) {
        this.hourly = hourly;
    }

    public boolean getDaily() {
        return daily;
    }

    public void setDaily(boolean daily) {
        this.daily = daily;
    }

    public boolean getWeekly() {
        return weekly;
    }

    public void setWeekly(boolean weekly) {
        this.weekly = weekly;
    }

    public boolean getMonthly() {
        return monthly;
    }

    public void setMonthly(boolean monthly) {
        this.monthly = monthly;
    }

    public boolean getYearly() {
        return yearly;
    }

    public void setYearly(boolean yearly) {
        this.yearly = yearly;
    }

    public boolean getSet_active() {
        return set_active;
    }

    public void setSet_active(boolean set_active) {
        this.set_active = set_active;
    }

    public String getReminder_content() {
        return reminder_content;
    }

    public void setReminder_content(String reminder_content) {
        this.reminder_content = reminder_content;
    }

}

