package com.example.demo.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.FlattenedContent;
import com.example.demo.util.CmsUtil;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.gson.Gson;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;

@Component
public class FilteredResponseTopicSubscriber {

	private static final String PROJECT_ID = ServiceOptions.getDefaultProjectId();
	
	@Autowired
	CmsUtil cmsUtil;
	
	public void startListener() {

		ProjectSubscriptionName subscription = ProjectSubscriptionName.of(PROJECT_ID, "filteredResponseSubscription");

		System.out.println("We are starting FilteredResponseTopicSubscriber here");

		MessageReceiver receiver = new MessageReceiver() {
			@Override
			public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
				String filteredResponse = message.getData().toStringUtf8();
				System.out.println("Message Received inside FilteredResponseTopicSubscriber : \n" + filteredResponse);
				//TODO Here we have decide the request format in which CMS will be submitted the final cognitive Profile
				String cmsApiResponse = cmsUtil.postCognitiveMetadata(filteredResponse);
				consumer.ack();

			}
		};

		Subscriber subscriber = null;
		try {
			subscriber = Subscriber.newBuilder(subscription, receiver).build();
			System.out.println("We have build FilteredResponseTopicSubscriber here");
			subscriber.addListener(new Subscriber.Listener() {
				@Override
				public void failed(Subscriber.State from, Throwable failure) {
					System.err.println(failure);
				}
			}, MoreExecutors.directExecutor());
			subscriber.startAsync().awaitRunning();
			// ...
		} finally {
			if (subscriber != null) {
				// subscriber.stopAsync();
			}
		}
	}
}
