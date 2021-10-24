package ggc.core;
public class Date{
    private int _days;
    private static int _now;

    static{ _now = 0; }
    public Date(int day){
        _days = day;
    }

    public Date add(int days){
        _days += days;
        return this;
    }

    public static void addNow(int days){
        _now += days;
    }
    public static Date now(){
        return new Date(_now);
    }
    public int getDay(){ return _days;}
    public String toString(){
        return "Data: " + Date._now + " dias.";
    }
}