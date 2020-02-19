package publisher.msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import publisher.msg.model.MessageData;

@SpringBootApplication
@RestController
@EnableBinding(Source.class)
public class PublisherApplication {
	
	@Autowired
	MessageChannel output;
	
	@PostMapping("/publish")
	public MessageData sendMessage(@RequestBody MessageData msg){
		output.send(MessageBuilder.withPayload(msg).build());
		return msg;
		
	}

	public static void main(String[] args) {
		SpringApplication.run(PublisherApplication.class, args);
	}

}
