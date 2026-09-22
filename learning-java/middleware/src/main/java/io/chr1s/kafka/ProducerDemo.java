package io.chr1s.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-06-07
 */
public class ProducerDemo {

    private static final String BROKER_LIST = "192.168.2.190:9092";
    private static final String TOPIC = "demo-topic";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("bootstrap.servers", BROKER_LIST);
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, "hello, Kafka!");
        try {
            producer.send(record, (metadata, ex) -> ex.printStackTrace());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            producer.close();
        }
        System.out.println("done!");
    }
}
