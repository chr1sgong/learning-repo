package io.chr1s.observer;

public class CustomObserverImpl implements CustomObserver {

    private String msg;

    @Override
    public void update(Object obj) {
        this.msg = obj.toString();
        System.out.println(msg);
    }
}
