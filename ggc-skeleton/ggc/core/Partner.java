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
    private List<Acquisition> _acquisitions;
    private List<Sale> _sales;
    private Set<Batch> _batches;
    private List<Notification> _notifications;

    protected Partner(String name, String id, String adress){
        _id = id;
        _name = name;
        _address = adress;
        _points = 0.0;
        _status = "NORMAL";
        _acquisitions = new ArrayList<>();
        _sales = new ArrayList<>();
        _batches = new TreeSet<>();
        _notifications = new ArrayList<>();
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

    public String getStatus(){
        return _status;
    }

    public Set<Batch> getBatches(){
        return new TreeSet<>(_batches);
    }

    public void addBatch(Batch batch){
        _batches.add(batch);
    }

    public void addSale(Sale sale){
        _sales.add(sale);
    }

    public void addAcquisition(Acquisition acq){
        _acquisitions.add(acq);
    }

    public int getAcquisitionValue(){
        double value = 0;
        for (Acquisition a: _acquisitions){
            value += a.getValue();
        }
        return (int) value;
    }

    public List<Acquisition> getAcquisitions(){
        return new ArrayList<>(_acquisitions);
    }

    public List<Notification> getNotifications(){
        return _notifications;
    }

    protected void clearNotifications(){
        _notifications.clear();
    }

    
    @Override
    public String toString(){
        return String.format("%s|%s|%s|%s|%d|%d|%d|%d", _id,_name,_address, _status,
                                                        _points.intValue(), getAcquisitionValue(),
                                                        _acquisitionsValue.intValue(),
                                                        _acquisitionsValue.intValue());
    }

    public void update(Notification n){
        _notifications.add(n);
    }
}
