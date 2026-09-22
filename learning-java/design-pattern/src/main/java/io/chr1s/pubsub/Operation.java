package io.chr1s.pubsub;

import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Map;

public class Operation extends Event {

    void subscribe(String channelName, Object subscriber) {
//        if (!channels.containsKey(channelName)) {
//            channels.put(channelName, new ConcurrentHashMap<>());
//        }
        channels.get(channelName).put(subscriber.hashCode(), new WeakReference<>(subscriber));
    }

    void publish(String channelName, Post message) {
        for (Map.Entry<Integer, WeakReference<Object>> subs : channels.get(channelName).entrySet()) {
            WeakReference<Object> subscribeRef = subs.getValue();
            Object subscribeObj = subscribeRef.get();
            for (final Method method : subscribeObj.getClass().getDeclaredMethods()) {
                Annotation annotation = method.getAnnotation(OnMessage.class);
                if (annotation != null) {
                    deliverMessage(subscribeObj, method, message);
                }
            }
        }
    }

    <T, P extends Post> boolean deliverMessage(T subscriber, Method method, Post message) {
        try {
            boolean methodFound = false;
            for (final Class paramClass : method.getParameterTypes()) {
                if (paramClass.equals(message.getClass())) {
                    methodFound = true;
                    break;
                }
            }
            if (methodFound) {
                method.setAccessible(true);
                method.invoke(subscriber, message);
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
