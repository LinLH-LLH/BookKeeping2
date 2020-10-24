package com.example.bookkeeping;

public class Money {

    private String title,subtitle,type;
    private int img,_id,year,month,date;

    public Money(int _id,String title,String subtitle,String type,int img,
    int year,int month,int date){
        this._id=_id;
        this.title=title;
        this.subtitle=subtitle;
        this.type=type;
        this.img=img;
        this.year=year;
        this.month=month;
        this.date=date;
    }
    public Money(int _id, String title, String subtitle, String type, int img){
        this._id=_id;
        this.title=title;
        this.subtitle=subtitle;
        this.type=type;
        this.img=img;
        this.year=year;
        this.month=month;
        this.date=date;
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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
