package com.inventory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.data.annotation.Id;

public class MongoData {

    @Id
    private String id;

    private String title;
    private String memo;
    private Date date;

    public MongoData(String title, String memo){
        this.title = title;
        this.memo = memo;
        this.date = Calendar.getInstance().getTime();
    }

    public String getTitle(){
        return title;
    }
    public String getMemo(){
        return memo;
    }
    public Date getDate(){
        return date;
    }
    public String getDateStr(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
    @Override
    public String toString(){
        return "「" + title + "」\n" + memo + "(" + date.toString() + ")";
    }
}