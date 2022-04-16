package com.upgrad.Booking.Config;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;


@Component
public class KafkaConfig implements KafkaInfoProducer {

    @Override
    public Producer<String, String> getProducer() throws IOException {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "ec2-18-207-88-18.compute-1.amazonaws.com:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("linger.ms", 0);
        properties.put("partitioner.class", "org.apache.kafka.clients.producer.internals.DefaultPartitioner");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("request.timeout.ms", 30000);
        properties.put("timeout.ms", 30000);
        properties.put("max.in.flight.requests.per.connection", 5);
        properties.put("retry.backoff.ms", 5);

        //Instantiate Producer Object
        return new KafkaProducer<>(properties);

    }
}
