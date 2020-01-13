package com.nabigeto.gavin.nabigetonabi.Reminder_Scheduler;

public class live_reminder_model {

    public String user_id;
    public String date;
    public String mtime;
    public String reminder_title;

    public int year;
    public int month;
    public int day;

    public int hour;
    public int minute;

    public Boolean hourly;
    public Boolean daily;
    public Boolean weekly;
    public Boolean monthly;
    public Boolean yearly;

    public Boolean set_active;

    public String reminder_content;

    public live_reminder_model(){};

    public live_reminder_model(String user_id, String date, String mtime, String reminder_title, int year, int month, int day, int hour, int minute, Boolean set_active, Boolean hourly, Boolean daily, Boolean weekly, Boolean monthly, Boolean yearly, String reminder_content){

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

    public Boolean getHourly() {
        return hourly;
    }

    public void setHourly(Boolean hourly) {
        this.hourly = hourly;
    }

    public Boolean getDaily() {
        return daily;
    }

    public void setDaily(Boolean daily) {
        this.daily = daily;
    }

    public Boolean getWeekly() {
        return weekly;
    }

    public void setWeekly(Boolean weekly) {
        this.weekly = weekly;
    }

    public Boolean getMonthly() {
        return monthly;
    }

    public void setMonthly(Boolean monthly) {
        this.monthly = monthly;
    }

    public Boolean getYearly() {
        return yearly;
    }

    public void setYearly(Boolean yearly) {
        this.yearly = yearly;
    }

    public Boolean getSet_active() {
        return set_active;
    }

    public void setSet_active(Boolean set_active) {
        this.set_active = set_active;
    }

    public String getReminder_content() {
        return reminder_content;
    }

    public void setReminder_content(String reminder_content) {
        this.reminder_content = reminder_content;
    }
}
