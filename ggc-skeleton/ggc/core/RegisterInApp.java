package ggc.core;

import java.io.Serializable;
import java.util.*;

public class RegisterInApp implements Serializable, DeliveryMethod{
    Set<Observer> _observers;

    public RegisterInApp(Set<Observer> observers){
        _observers = observers;
    }
    public void deliver(Notification n){
        for (Observer obs: _observers){
			obs.update(n);
		}
    }
}
