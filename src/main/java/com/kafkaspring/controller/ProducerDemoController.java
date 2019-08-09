package com.kafkaspring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.kafkaspring.service.ProducerDemo;

import org.apache.kafka.common.serialization.StringSerializer;

@Controller
@RequestMapping("/kafka")
public class ProducerDemoController {
	
	
    private final ProducerDemo producerDemo;
    
	@Autowired
	public ProducerDemoController(ProducerDemo producerDemo) {
		
		this.producerDemo = producerDemo;
		
	}
	
	@GetMapping("/")
	public String getKafkaPage() {
		
		return "kafka/index";
	}
	
	@GetMapping("/publish/{message}")
	public String publishData(@PathVariable("message") String message,Model m){
		
		//sending data to kafka topic
		//System.out.println("In Publish Message");
		//System.out.println("Producer is >>>"+this.producerDemo.toString());
		producerDemo.sendMessage(message);
		
		m.addAttribute("message",message);
		
		//System.out.println("Message Published Successfully");
		
		return "kafka/publish";
		
	}
	
	
}
