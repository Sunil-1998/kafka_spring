package com.kafkaspring.service;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerDemo {
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	
	private static final String TOPIC = "Kafka_New";
	
	public void sendMessage(String message){
		System.out.println("In Send Message");
		try {
			//System.out.println("Kafka Template is >>>"+this.kafkaTemplate.toString());
			this.kafkaTemplate.send(TOPIC, message);
	        this.kafkaTemplate.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}
        
    }
	
	public void sendSimpleMessage(String message) {
		Properties properties = new Properties();
		  properties.setProperty("bootstrap.servers", "localhost:9092");
		  properties.setProperty("key.serializer", StringSerializer.class.getName());
		  properties.setProperty("value.serializer",StringSerializer.class.getName());
		  properties.setProperty("acks", "1"); 
		  properties.setProperty("retries", "3");
		  properties.setProperty("linger.ms", "1");
		  
		  Producer<String, String> producer = new KafkaProducer<>(properties);
		  
		  ProducerRecord<String, String> producerRecord = new ProducerRecord<String,String>("Kafka_Simple", "Hello Producer");
		  
		  producer.send(producerRecord); 
		  producer.close();
	}
}
