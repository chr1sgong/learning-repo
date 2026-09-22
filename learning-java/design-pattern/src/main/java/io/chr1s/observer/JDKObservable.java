package io.chr1s.observer;

import java.util.Observable;

public class JDKObservable extends Observable {

    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
        setChanged();
        notifyObservers(msg);
    }

    public static void main(String[] args) {
        JDKObservable observable = new JDKObservable();
        observable.addObserver(new JDKObserver());
        observable.addObserver(new JDKObserver());

        observable.setMsg("hello, world");
    }
}
