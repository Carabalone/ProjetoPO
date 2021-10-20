public class Date{
    private int _days;
    priva static int _now;

    static{ _now = 0; }
    public Date(int day){
        _days = day;
    }

    public Date add(int days){
        _days += days;
        return this;
    }

    public static void addNow(int days){
        now += days;
    }
    public static Date now(){
        return new Date(_now);
    }
}