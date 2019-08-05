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


	private static final String TOPIC = "My_Topic";

	public void sendMessage(String message) {
		
		
		System.out.println("In Send Message");
		System.out.println("Bootstrap Address is >>>"+bootstrapAddress);

		/*
		 * Properties properties = new Properties(); properties.put("bootstrap.servers",
		 * "localhost:9092"); properties.put("connections.max.idle.ms", 10000);
		 * properties.put("request.timeout.ms", 5000); try (AdminClient client =
		 * KafkaAdminClient.create(properties)) { ListTopicsResult topics =
		 * client.listTopics(); Set<String> names = topics.names().get(); if
		 * (names.isEmpty()) { // case: if no topic found.
		 * System.out.println("Topics Found >>>"+names.toString()); } } catch (Exception
		 * e) { // Kafka is not available e.printStackTrace();
		 * System.out.println("Topic Fetching Failed"); }
		 */
		try {
			// System.out.println("Kafka Template is >>>"+this.kafkaTemplate.toString());
			this.kafkaTemplate.send(TOPIC, message);
			this.kafkaTemplate.flush();
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
