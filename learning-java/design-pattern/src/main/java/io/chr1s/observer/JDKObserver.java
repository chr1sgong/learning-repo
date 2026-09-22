package io.chr1s.observer;

import java.util.Observable;
import java.util.Observer;

public class JDKObserver implements Observer {

    private Object msg;

    @Override
    public void update(Observable o, Object msg) {
        this.msg = msg;
        System.out.println(msg.toString());
    }
}
