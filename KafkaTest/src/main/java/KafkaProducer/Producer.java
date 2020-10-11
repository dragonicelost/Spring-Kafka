package KafkaProducer;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	private static final Logger Logger =  LoggerFactory.getLogger(Producer.class);
			
			
	@Autowired
	KafkaTemplate<String, String> stringTemplate;
	
	public static  void sendMessageToTopic(String message)
	{
		Logger.info("sending message");
		//stringTemplate.s
		
		Logger.info("send message");
	}

}
