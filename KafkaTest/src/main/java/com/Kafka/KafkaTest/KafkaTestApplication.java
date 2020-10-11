package com.Kafka.KafkaTest;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.Kafka.KafkaTest.KafkaProducer.Producers;


@SpringBootApplication
public class KafkaTestApplication  implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(KafkaTestApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(KafkaTestApplication.class, args);
	}
	
	
	@Autowired
	Producers producers;
	
//	@Value(value = "${name}")
//    private String name;
	
	public void run(String... args) throws Exception {
		System.out.println("hello");
		producers.sendMessageToTopic("hello World");
		producers.CreateTopic("tests");
		logger.info("ended");
		
	}


//	@Value(value = "${kafka.bootstrapAddress}")
//    private String bootstrapAddress;
	
	@Bean
	public KafkaAdmin admin() {
	    Map<String, Object> configs = new HashMap<String, Object>();
	    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
	   // configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress);gfn
	    return new KafkaAdmin(configs);
	}
	
	@Bean
	public NewTopic topic()
	{
		NewTopic t = new NewTopic("tests",2,(short) 2);
		return t;
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
//	
	@Bean
	public ProducerFactory<String,String> producer()
	{
		return new DefaultKafkaProducerFactory<String, String>(getConfig());
		
	}
	
	@Bean
	public Map<String,Object> getConfig()
	{
		Map<String,Object> config = new HashMap<String,Object>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return config;
	}
	
	@Bean
	public KafkaTemplate<String, String> template()
	{
		return new KafkaTemplate<String, String>(producer());
	}
//	
//	
//
//


	

}
