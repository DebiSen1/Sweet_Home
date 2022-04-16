package com.upgrad.Booking.Config;

import org.apache.kafka.clients.producer.Producer;

import java.io.IOException;

public interface KafkaInfoProducer {

    Producer<String, String> getProducer() throws IOException;

}
