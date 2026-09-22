package io.chr1s.pubsub;

public class PubSubTest {

    public static void main(String[] args) {
        Subscriber sub1 = new Subscriber(1);
        Subscriber sub2 = new Subscriber(2);

        Subscriber sub3 = new Subscriber(3);
        Subscriber sub4 = new Subscriber(4);

        Event.operation.subscribe("action#create", sub1);
        Event.operation.subscribe("action#create", sub2);

        Event.operation.subscribe("action#update", sub3);
        Event.operation.subscribe("action#delete", sub4);

        Message msg1 = new Message("create action");
        Message msg2 = new Message("update action");
        Event.operation.publish("action#create", msg1);
        Event.operation.publish("action#update", msg2);
    }
}
