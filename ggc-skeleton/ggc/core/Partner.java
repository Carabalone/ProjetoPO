package ggc.core;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Partner{
    private String _name;
    private String _id;
    private String _address;
    private String _status;
    private double _points;
    private int _acquisitionsValue;
    private int _salesValue;
    private int _paidSalesValue;
    private List<Acquisition> _acquisition;
    private List<Sale> _sales;
    private List<Batch> _batches;

    protected Partner(String name, String adress){
        _id = name;
        _name = name;
        _address = adress;
        _points = 0;
        _status = "Normal";
        _acquisition = new ArrayList();
        _sales = new ArrayList();
        _batches = new ArrayList();
        _acquisitionsValue = 0;
        _paidSalesValue = 0;
        _salesValue = 0;
    }
    public String getId(){
        return _id;
    }
    
    //TODO round up last values
    @Override
    public String toString(){
        return String.format("%s|%s|%s|%f|%s|%d|%d|%d", _id,_name,_address,_points,_status,_acquisitionsValue,_salesValue,_paidSalesValue);
    }
}
