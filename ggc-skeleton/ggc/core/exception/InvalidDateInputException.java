package ggc.core.exception;

public class InvalidDateInputException extends Exception{
    private int _date;

    public InvalidDateInputException(int date){
        _date = date;
    }

    public int getDate(){
        return _date;
    }
}
