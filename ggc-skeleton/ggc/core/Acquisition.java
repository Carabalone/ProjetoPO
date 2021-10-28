package ggc.core;

import java.io.Serializable;

public class Acquisition extends Transaction implements Serializable{
    Partner _partner;
    protected Acquisition(Product p, int quantity, Partner intPart){
        super(quantity, Date.now(), p);
        _partner = intPart;

    }
    //TODO
    public String toString(){
        return "TODO";
    }
}