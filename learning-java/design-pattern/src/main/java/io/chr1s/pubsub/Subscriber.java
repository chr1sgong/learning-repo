package io.chr1s.pubsub;

public class Subscriber {

    int id;

    public Subscriber(int id) {
        this.id = id;
    }

    @OnMessage
    private void onMessage(Message message) {
        System.out.println(message.getMessage());
    }
}
