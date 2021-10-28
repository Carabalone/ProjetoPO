package ggc.core;

import java.io.Serializable;
import java.nio.file.ProviderNotFoundException;

public abstract class Sale extends Transaction implements Serializable{
    Partner _partner;
    protected Sale(Product p, int quantity, Partner intPart){
        super(quantity, p);
        _partner = intPart;
    }
}
