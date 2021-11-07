package ggc.core;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.Serializable;
import java.lang.*;

public class Partner implements Comparable<Partner>, Serializable, Observer{
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
    private TreeSet<Batch> _batches;
    private List<Notification> _notifications;

    protected Partner(String name, String id, String adress){
        _id = id;
        _name = name;
        _address = adress;
        _points = 0.0;
        _status = "NORMAL";
        _acquisition = new ArrayList();
        _sales = new ArrayList();
        _batches = new TreeSet();
        _acquisitionsValue = 0.0;
        _paidSalesValue = 0.0;
        _salesValue = 0.0;
    }


    public int compareTo(Partner p){
        return _id.compareToIgnoreCase(p.getId().toLowerCase());
    }

    
    public String getId(){
        return _id;
    }

    public TreeSet<Batch> getBatches(){
        return new TreeSet(_batches);
    }

    public void addBatch(Batch batch){
        _batches.add(batch);
    }
    
    //TODO round up last values
    @Override
    public String toString(){
        return String.format("%s|%s|%s|%s|%d|%d|%d|%d", _id,_name,_address, _status,
                                                        _points.intValue(), _acquisitionsValue.intValue(),
                                                        _acquisitionsValue.intValue(),
                                                        _acquisitionsValue.intValue());
    }

    public void update(Notification n){
        _notifications.add(n);
    }
}
