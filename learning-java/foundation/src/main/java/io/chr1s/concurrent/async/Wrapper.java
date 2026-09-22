package io.chr1s.concurrent.async;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-01-18
 */
public class Wrapper {

    private Object param;
    private Worker worker;
    private Listener listener;

    public Object getParam() {
        return param;
    }

    public Wrapper setParam(Object param) {
        this.param = param;
        return this;
    }

    public Worker getWorker() {
        return worker;
    }

    public Wrapper setWorker(Worker worker) {
        this.worker = worker;
        return this;
    }

    public Listener getListener() {
        return listener;
    }

    public Wrapper addListener(Listener listener) {
        this.listener = listener;
        return this;
    }
}
