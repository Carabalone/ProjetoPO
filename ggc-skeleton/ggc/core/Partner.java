package ggc.core;

import java.util.*;

public class Partner{
    private String _name;
    private String _id;
    private String _address;
    private String _status;
    private double _points;
    private List<Acquisition> _acquisition;
    private List<Sale> _sales;
    private List<Batch> _batches;

    public String getId(){
        return _id;
    }
}
