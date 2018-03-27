package com.example.demo.messaging;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.gson.Gson;
import com.example.demo.pojo.ContentRequest;
import com.example.demo.util.CmsUtil;
import com.example.demo.util.WatsonUtil;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;

@Component
public class TopicSubscriber {

	private static final String PROJECT_ID = ServiceOptions.getDefaultProjectId();
	
	@Autowired
	CmsUtil cmsUtil;
	
	@Autowired
	static
	WatsonUtil watsonUtil;
	
	@Value("${watson.nlu.api.url}")
	private String watsonNLUAPIUrl;
	
	/*@Value("${ibm.developer.account.user.name}")*/
	private static String ibmAccountUserName = "d7c25a66-a74a-4d20-848f-fe52238491c4";
	
	
	/*@Value("${ibm.developer.account.password}")*/
	private static String ibmAccountPassword = "Y1F2bSIaug37";


	private static final BlockingQueue<PubsubMessage> messages = new LinkedBlockingDeque<>();


	static class MessageReceiverExample implements MessageReceiver {

		@Override
		public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
			messages.offer(message);
			System.out.println("Message Received: \n" + message.getData().toStringUtf8());
			Gson gson = new Gson();
			String flattenedContent;
			String profileMeteData = null;
			ContentRequest[] contentRequests = gson.fromJson(message.getData().toStringUtf8(), ContentRequest[].class);
			for(ContentRequest contentRequest : contentRequests) {
				//flattenedContent = cmsUtil.getFlattenedContent(contentRequest.getCmsContentId());
				flattenedContent = getFlattenedContent();
				profileMeteData = watsonUtil.getTextMetadata(flattenedContent,contentRequest, ibmAccountUserName, ibmAccountPassword);
				System.out.println("Profile Metadata is : \n" + profileMeteData);
			}
			consumer.ack();
			
		}
	}

	public void startListener() throws InterruptedException {

		String subscriptionId = "testSubscription";

		ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(PROJECT_ID, subscriptionId);
		Subscriber subscriber = null;
		try {
			// create a subscriber bound to the asynchronous message receiver
			subscriber = Subscriber.newBuilder(subscriptionName, new MessageReceiverExample()).build();
			subscriber.addListener(new Subscriber.Listener() {
				@Override
				public void failed(Subscriber.State from, Throwable failure) {
					// Handle failure. This is called when the Subscriber encountered a fatal error
					// and is shutting down.
					System.err.println(failure);
				}
			}, MoreExecutors.directExecutor());

			subscriber.startAsync().awaitRunning();
			// Continue to listen to messages

			/*
			 * while (true) { System.out.println("Inside While Loop"); PubsubMessage message
			 * = messages.take(); System.out.
			 * println("We are inside Topic Subscriber and will invoke watson API");
			 * System.out.println("Message Id: " + message.getMessageId());
			 * System.out.println("Data: " + message.getData().toStringUtf8()); }
			 */
		} finally {
			/*
			 * if (subscriber != null) { subscriber.stopAsync(); }
			 */
		}
	}
	
	// remove when able to autowire CmsUtil correctly
	//TODO
	private static String getFlattenedContent() {
		// TODO
		String hardCodedText = "IBM is an American multinational technology company headquartered in Armonk, New York, United States, with operations in over 170 countries";
		return hardCodedText;
	}
}
