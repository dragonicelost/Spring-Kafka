package com.Kafka.KafkaTest.KafkaProducer;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Producers {
	private static final Logger Logger =  LoggerFactory.getLogger(Producers.class);
			
	
	@Autowired
	 KafkaTemplate<String, String> template;
	
	public   void sendMessageToTopic(String message)
	{
		Logger.info("sending message");
		//stringTemplate.s
		template.send("test", message+" --- from ecliplse");
		Logger.info("send message");
	}
	
	

	public   void CreateTopic(String topic)
	{
		Logger.info("sending message");
		//stringTemplate.s
		template.send(topic, topic+" --- created Topic  from ecliplse");
		Logger.info("send message");
	}
	
	

}
