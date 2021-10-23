package ggc.core;

import java.nio.file.ProviderNotFoundException;

public abstract class Sale extends Transaction{
    Partner _partner;
    protected Sale(Product p, int quantity, Partner intPart){
        super(quantity, p);
        _partner = intPart;
    }
}
