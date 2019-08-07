package com.kafkaspring.service;

import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class ProducerDemo {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${spring.kafka.bootstrapAddress}")
	String bootstrapAddress;
	
	@Value("${kafka.custom.myTopic}")
	String TOPIC;

	public void sendMessage(String message) {
		
		
		System.out.println(" In send Message Bootstrap Address is >>> "+bootstrapAddress);

		try {
			kafkaTemplate.send(TOPIC, message);
			kafkaTemplate.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	public void sendSimpleMessage(String message) {
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", bootstrapAddress);
		properties.setProperty("key.serializer", StringSerializer.class.getName());
		properties.setProperty("value.serializer", StringSerializer.class.getName());
		properties.setProperty("acks", "1");
		properties.setProperty("retries", "3");
		properties.setProperty("linger.ms", "1");

		Producer<String, String> producer = new KafkaProducer<>(properties);

		ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(TOPIC, "Hello Producer");

		producer.send(producerRecord);
		producer.close();
	}

}
