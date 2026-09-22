package io.chr1s.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-06-07
 */
public class ConsumerDemo {

    private static final String BROKER_LIST = "192.168.2.190:9092";
    private static final String TOPIC = "demo-topic";
    private static final String groupId = "demo-group";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("bootstrap.servers", BROKER_LIST);
        properties.put("group.id", groupId);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
//        consumer.subscribe(Collections.singleton(TOPIC));
        TopicPartition tp = new TopicPartition(TOPIC, 0);
        consumer.assign(Arrays.asList(tp));
        long lastConsumedOffset = -1;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            if (records.isEmpty()) {
                break;
            }
            List<ConsumerRecord<String, String>> partitionRecords = records.records(tp);
            lastConsumedOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
            consumer.commitSync();
            for (ConsumerRecord<String, String> record : partitionRecords) {
                System.out.println(record.value());
            }
            System.out.println("consumed offset is " + lastConsumedOffset);
            OffsetAndMetadata offsetAndMetadata = consumer.committed(tp);
            System.out.println("committed offset is " + offsetAndMetadata.offset());
            long position = consumer.position(tp);
            System.out.println("the offset of next record is " + position);
        }
    }
}
