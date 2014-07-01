package com.oogie;

import java.util.ArrayList;

public class Publisher {
    private ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();
    
    public void add(Subscriber s) {
        subscribers.add(s);
    }
    
    public void publish(String message) {
        for(Subscriber s : subscribers) {
            s.receive(message);
        }
    }
}
