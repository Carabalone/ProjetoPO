package ggc.core;

// This is part of a Strategy design Pattern so the program can deliver notifications through different methods of delivery.
// By omission the DeliveryMethod will be just registering the Notification in the program. 
public interface DeliveryMethod{
    void deliver(Notification n);
}