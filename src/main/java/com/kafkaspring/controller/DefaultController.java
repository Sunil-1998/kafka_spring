package com.kafkaspring.controller;

import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
	@Value("${spring.kafka.bootstrapAddress}")
	String bootstrapAddress;
	
	@Value("${kafka.custom.myTopic}")
	String TOPIC;
	
	@GetMapping("/")
	public String getIndex() {

		/*
		 * Properties properties = new Properties();
		 * properties.setProperty("bootstrap.servers", "localhost:9092");
		 * properties.setProperty("key.serializer", StringSerializer.class.getName());
		 * properties.setProperty("value.serializer",StringSerializer.class.getName());
		 * //Set acknowledgements for producer requests. properties.put("acks", "all");
		 */
		
		// create instance for properties to access producer configs   
	      Properties props = new Properties();
	      
	      //Assign localhost id
	      props.put("bootstrap.servers", "localhost:9092");
	      
	      //Set acknowledgements for producer requests.      
	      props.put("acks", "all");
	      
	      //If the request fails, the producer can automatically retry,
	      props.put("retries", 0);
	      
	      //Specify buffer size in config
	      props.put("batch.size", 16384);
	      
	      //Reduce the no of requests less than 0   
	      props.put("linger.ms", 1);
	      
	      //The buffer.memory controls the total amount of memory available to the producer for buffering.   
	      props.put("buffer.memory", 33554432);
	      
	      props.put("key.serializer", 
	    		  StringSerializer.class.getName());
	         
	      props.put("value.serializer", 
	    		  StringSerializer.class.getName());
		
		  
		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		  
		ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(TOPIC, "Hello Producer");
		  
		  producer.send(producerRecord); 
		  producer.flush();
		  producer.close();
		  
		
		return "index";
	}
}
