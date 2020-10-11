package com.Kafka.KafkaTest;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;

import KafkaProducer.Producer;

@SpringBootApplication
public class KafkaTestApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(KafkaTestApplication.class, args);
	}
	
	@Autowired
	 Producer producer;
	
	@Value(value = "${name}")
    private String name;
	
	public void run(String... args) throws Exception {
		System.out.println("hello");
		Producer.sendMessageToTopic("hello World");
		System.out.println(name);
		
	}
	

	@Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
	
	@Bean
	public KafkaAdmin admin() {
	    Map<String, Object> configs = new HashMap<String, Object>();
	    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
	   // configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress);
	    return new KafkaAdmin(configs);
	}
	
	
	@Bean
	public NewTopic enterMessage(String message)
	{
		short replica=2;
		NewTopic topic = new NewTopic(message, 2, replica);
		return topic;
	}
	
//	@Bean
//	public NewTopic topic1(String TopicName) {
//	    return TopicBuilder.name(TopicName)
//	            .partitions(10)
//	            .replicas(3)
//	            .compact()
//	            .build();
//	}
//	
	
	
	
	@Bean
	public ProducerFactory<String, String> producerFactory() {
	    return new DefaultKafkaProducerFactory(producerConfigs());
	}

	@Bean
	public Map<String, Object> producerConfigs() {
	    Map<String, Object> props = new HashMap();
	    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	    // See https://kafka.apache.org/documentation/#producerconfigs for more properties
	    return props;
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
	    return new KafkaTemplate<String, String>(producerFactory());
	}
	
	
	@Bean
	public KafkaTemplate<String, String> stringTemplate(ProducerFactory<String, String> pf) {
	    return new KafkaTemplate(pf);
	}





	

}
