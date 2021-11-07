package ggc.core;

import java.util.*;

public class Notification {

    private Type _type;
    private Product _product;

    protected Notification(Type type, Product product){
        _type = type;
        _product = product;
    }
}
