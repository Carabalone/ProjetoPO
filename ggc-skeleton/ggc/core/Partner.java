package ggc.core;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.lang.*;

public class Partner{
    private String _name;
    private String _id;
    private String _address;
    private String _status;
    private Double _points;
    private Double _acquisitionsValue;
    private Double _salesValue;
    private Double _paidSalesValue;
    private List<Acquisition> _acquisition;
    private List<Sale> _sales;
    private List<Batch> _batches;

    protected Partner(String name, String id, String adress){
        _id = id;
        _name = name;
        _address = adress;
        _points = 0.0;
        _status = "Normal";
        _acquisition = new ArrayList();
        _sales = new ArrayList();
        _batches = new ArrayList();
        _acquisitionsValue = 0.0;
        _paidSalesValue = 0.0;
        _salesValue = 0.0;
    }
    public String getId(){
        return _id;
    }
    
    //TODO round up last values
    @Override
    public String toString(){
        return String.format("%s|%s|%s|%d|%s|%d|%d|%d", _id,_name,_address, _points.intValue(),
                                                        _status, _acquisitionsValue.intValue(),
                                                        _acquisitionsValue.intValue(),
                                                        _acquisitionsValue.intValue());
    }
}
