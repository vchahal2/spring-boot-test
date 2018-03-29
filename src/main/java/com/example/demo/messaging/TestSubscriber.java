package com.example.demo.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.ContentRequest;
import com.example.demo.util.CmsUtil;
import com.example.demo.util.WatsonUtil;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.gson.Gson;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;

@Component
public class TestSubscriber {

	@Autowired
	CmsUtil cmsUtil;
	
	@Autowired
	WatsonUtil watsonUtil;
	
	@Value("${watson.nlu.api.url}")
	private String watsonNLUAPIUrl;
	
	@Value("${ibm.developer.account.user.name}")
	private  String ibmAccountUserName ;
	
	@Value("${ibm.developer.account.password}")
	private  String ibmAccountPassword ;
	
	private static final String PROJECT_ID = ServiceOptions.getDefaultProjectId();
	
	public void startListener() {

		ProjectSubscriptionName subscription = ProjectSubscriptionName.of(PROJECT_ID, "testSubscription");
		
		System.out.println("We are starting Topic Subscriber here"); 

		MessageReceiver receiver = new MessageReceiver() {
			@Override
			public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
				System.out.println("got message: " + message.getData().toStringUtf8());
				
				
				System.out.println("Message Received: \n" + message.getData().toStringUtf8());
				Gson gson = new Gson();
				String flattenedContent;
				String profileMeteData = null;
				ContentRequest[] contentRequests = gson.fromJson(message.getData().toStringUtf8(), ContentRequest[].class);
				for(ContentRequest contentRequest : contentRequests) {
					//flattenedContent = cmsUtil.getFlattenedContent(contentRequest.getCmsContentId());
					//flattenedContent = getFlattenedContent();
					flattenedContent= cmsUtil.getFlattenedContent(contentRequest.getCmsContentId());
					profileMeteData = watsonUtil.getTextMetadata(flattenedContent,contentRequest, ibmAccountUserName, ibmAccountPassword);
					System.out.println("Profile Metadata is : \n" + profileMeteData);
				}
				consumer.ack();
				
				
			}
		};

		Subscriber subscriber = null;
		try {
			subscriber = Subscriber.newBuilder(subscription, receiver).build();
			System.out.println("We have build the Subscriber here");
			subscriber.addListener(new Subscriber.Listener() {
				@Override
				public void failed(Subscriber.State from, Throwable failure) {
					// Handle failure. This is called when the Subscriber encountered a fatal error
					// and is shutting down.
					System.err.println(failure);
				}
			}, MoreExecutors.directExecutor());
			subscriber.startAsync().awaitRunning();
			// ...
		} finally {
			if (subscriber != null) {
				//subscriber.stopAsync();
			}
		}
	}
}
