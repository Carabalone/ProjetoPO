package ggc.core;

import java.io.Serializable;

import ggc.app.exception.InvalidDateException;

public class Date  implements Serializable{
    /*used if creating a Date that is not in the present is needed*/
    private int _days;
    /*the actual date of the program*/
    private static int _now;

    static{ _now = 0; }
    public Date(int day){
        _days = day;
    }

    /*
    * @@param days the days to advance in the _days variable
    **/
    public Date add(int days){
        _days += days;
        return this;
    }

    /*
    * @@param days the days to advance in the _now variable
    **/
    public static void addNow(int days){
        _now += days;
    }

    public static Date now(){
        return new Date(_now);
    }
    /*
    * return The current date but as an int
    */
    public static int showNow(){
        return Date._now;
    }
    
    public int getDay(){
        return _days;
    }

    public String toString(){
        return "Data: " + Date._now + " dias.";
    }
}