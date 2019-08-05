package com.kafkaspring.config;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.kafkaspring.service.ProducerDemo;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerConfigDemo {
		
	@Bean
	public ProducerFactory<String, String> producerFactory() {
		
	      Map<String, Object> configProps = new HashMap<>();
	      configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	      configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	      configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	      return new DefaultKafkaProducerFactory<>(configProps);
	   }
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		
	      return new KafkaTemplate<>(producerFactory());
	      
	   }
	
}