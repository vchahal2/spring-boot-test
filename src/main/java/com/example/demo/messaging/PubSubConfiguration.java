/*package com.example.demo.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gcp.pubsub.core.PubSubOperations;
import org.springframework.cloud.gcp.pubsub.integration.AckMode;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.cloud.gcp.pubsub.support.GcpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.google.cloud.pubsub.v1.AckReplyConsumer;

@Configuration
public class PubSubConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(PubSubConfiguration.class);

	  @Bean
	  public MessageChannel pubsubInputChannel() {
	    return new DirectChannel();
	  }

	  @Bean
	  public PubSubInboundChannelAdapter messageChannelAdapter(
	      @Qualifier("pubsubOutputChannel") MessageChannel inputChannel,
	      PubSubOperations pubSubTemplate) {
		  LOGGER.info("We are inside messageChannelAdapter method " );
	    PubSubInboundChannelAdapter adapter =
	        new PubSubInboundChannelAdapter(pubSubTemplate, "testSubscription");
	    adapter.setOutputChannel(inputChannel);
	    adapter.setAckMode(AckMode.MANUAL);

	    return adapter;
	  }

	  @Bean
	  @ServiceActivator(inputChannel = "pubsubOutputChannel")
	  public MessageHandler messageReceiver() {
	    return message -> {
	      LOGGER.info("Message arrived! Payload: " + message.getPayload());
	      AckReplyConsumer consumer =
	          (AckReplyConsumer) message.getHeaders().get(GcpHeaders.ACKNOWLEDGEMENT);
	      consumer.ack();
	    };
	  }

	  @Bean
	  @ServiceActivator(inputChannel = "pubsubInputChannel")
	  public MessageHandler messageSender(PubSubOperations pubsubTemplate) {
		  LOGGER.info("inside message sender");
	    return new PubSubMessageHandler(pubsubTemplate, "testTopic");
	  }

	  @MessagingGateway(defaultRequestChannel = "pubsubInputChannel")
	  public interface PubSubGateway {

	    void sendToPubsub(String text);
	  }

}
*/