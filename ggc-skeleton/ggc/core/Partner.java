package ggc.core;

import java.util.*;
import java.io.Serializable;

public class Partner implements Comparable<Partner>, Serializable, Observer{
    private String _name;
    private String _id;
    private String _address;
    private Status _status;
    private Double _points;
    private List<Acquisition> _acquisitions;
    private List<Sale> _sales;
    private Set<Batch> _batches;
    private List<Notification> _notifications;

    protected Partner(String name, String id, String adress){
        _id = id;
        _name = name;
        _address = adress;
        _points = 0.0;
        _status = Status.NORMAL;
        _acquisitions = new ArrayList<>();
        _sales = new ArrayList<>();
        _batches = new TreeSet<>();
        _notifications = new ArrayList<>();
    }


    public int compareTo(Partner p){
        return _id.compareToIgnoreCase(p.getId().toLowerCase());
    }

    
    protected String getId(){
        return _id;
    }

    protected Status getStatus(){
        return _status;
    }

    protected Set<Batch> getBatches(){
        return new TreeSet<>(_batches);
    }

    protected void addBatch(Batch batch){
        _batches.add(batch);
    }

    protected void addSale(Sale sale){
        _sales.add(sale);
    }

    protected int getSalesValue(){
        double value = 0;
        for (Sale s: _sales){
            if (s instanceof SaleByCredit){
                value += s.getAmountPaid();
            }
        }
        return (int) value;
    }

    protected int getPaidSalesValue(){
        double value = 0;
        for (Sale s: _sales){
            if (s instanceof SaleByCredit && s.isPaid()){
                value += s.getAmountPaid();
            }
        }
        return (int) value;
    }

    protected List<Sale> getSales(){
        return _sales;
    }

    public void addAcquisition(Acquisition acq){
        _acquisitions.add(acq);
    }

    protected int getAcquisitionValue(){
        double value = 0;
        for (Acquisition a: _acquisitions){
            value += a.getValue();
        }
        return (int) value;
    }

    protected List<Acquisition> getAcquisitions(){
        return new ArrayList<>(_acquisitions);
    }

    protected List<Notification> getNotifications(){
        return _notifications;
    }

    protected void clearNotifications(){
        _notifications.clear();
    }

    protected void updateStatus(){
        if (_points > 25000)
            _status = Status.ELITE;
        else if (_points > 2000)
            _status = Status.SELECTION;
    }

    protected void updateStatus(int delay){
        if (_status == Status.ELITE && delay > 15){
            _status = Status.SELECTION;
            _points *= 0.25;
        }
        else if (_status == Status.SELECTION && delay > 2){
            _status = Status.NORMAL;
            _points *= 0.1;
        }
        else if (_status == Status.NORMAL && delay > 0)
            _points = 0d;
    }

    protected void addPoints(double points){
        _points += points;
        updateStatus();
    }

    
    @Override
    public String toString(){
        return String.format("%s|%s|%s|%s|%d|%d|%d|%d", _id,_name,_address, _status.name(),
                                                        _points.intValue(), getAcquisitionValue(),
                                                        getSalesValue(),
                                                        getPaidSalesValue());
    }

    public void update(Notification n){
        _notifications.add(n);
    }
}
