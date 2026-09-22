package io.chr1s.concurrent.async;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-01-18
 */
public interface Worker {

    String action(Object object) throws InterruptedException;
}
