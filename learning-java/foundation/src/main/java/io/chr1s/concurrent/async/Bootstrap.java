package io.chr1s.concurrent.async;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-01-18
 */
public class Bootstrap {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();

        Worker worker = bootstrap.newWorker();

        Wrapper wrapper = new Wrapper()
                .setWorker(worker)
                .setParam("hello");

        bootstrap.doWork(wrapper).addListener((result) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(result);
        });
        System.out.println(Thread.currentThread().getName());
    }

    private Wrapper doWork(Wrapper wrapper) {
        new Thread(() -> {
            Worker worker = wrapper.getWorker();
            String result = null;
            try {
                result = worker.action(wrapper.getParam());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrapper.getListener().result(result);
        }).start();
        return wrapper;
    }

    private Worker newWorker() {
        return object -> {
            Thread.sleep(2000);
            return object + " world";
        };
    }

}
