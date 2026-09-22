package io.chr1s.pubsub;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Event {

    static {
        init();
    }

    static Operation operation;

    static Map<String, Map<Integer, WeakReference<Object>>> channels;

    static void init() {
        channels = new ConcurrentHashMap<>();
        operation = new Operation();
    }
}
