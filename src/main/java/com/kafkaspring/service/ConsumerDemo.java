package com.kafkaspring.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerDemo {

	@KafkaListener(topics = "Kafka_New", groupId = "helloworld", containerFactory = "kafkaListenerContainerFactory")
	public void  consumeData(ConsumerRecord<String, String > record) throws Exception {
		System.out.println("Consumed "+record.toString());

	}
}
