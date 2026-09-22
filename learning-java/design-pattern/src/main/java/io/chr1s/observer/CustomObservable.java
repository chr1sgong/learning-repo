package io.chr1s.observer;

import java.util.ArrayList;
import java.util.List;

public class CustomObservable {

    private String msg;

    private List<CustomObserver> observerList;

    public CustomObservable(List<CustomObserver> observerList) {
        this.observerList = observerList;
    }

    public void addObserver(CustomObserver observer) {
        observerList.add(observer);
    }

    public void removeObserver(CustomObserver observer) {
        observerList.remove(observer);
    }

    public void setMsg(String msg) {
        this.msg = msg;
        for (CustomObserver observer : observerList) {
            observer.update(msg);
        }
    }

    public String getMsg() {
        return this.msg;
    }

    public static void main(String[] args) {
        CustomObservable observable = new CustomObservable(new ArrayList<>());
        observable.addObserver(new CustomObserverImpl());
        observable.addObserver(new CustomObserverImpl());
        observable.setMsg("hello, world");
    }
}
