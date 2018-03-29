package com.example.demo.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.FlattenedContent;
import com.example.demo.util.FilterUtil;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.gson.Gson;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;

@Component
public class UnfilteredResponseTopicSubscriber {

	private static final String PROJECT_ID = ServiceOptions.getDefaultProjectId();

	@Autowired
	FilterUtil filterUtil;
	
	@Autowired
	FilteredResponseTopicPublisher filteredResponseTopicPublisher;

	public void startListener() {

		ProjectSubscriptionName subscription = ProjectSubscriptionName.of(PROJECT_ID, "unfilteredResponseSubscription");

		System.out.println("We are starting UnfilteredResponseTopicSubscriber here");

		MessageReceiver receiver = new MessageReceiver() {
			@Override
			public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
				String unfilteredCognitiveMetadata  = message.getData().toStringUtf8();
				System.out.println("*****UnfilteredResponseTopicSubscriber****Message Received: \n" + unfilteredCognitiveMetadata);

				String filteredCognitiveMetadata = filterUtil.filter(unfilteredCognitiveMetadata);
				System.out.println("Filtered Content inside UnfilteredResponseTopicSubscriber: \n" + filteredCognitiveMetadata);
				try {
					filteredResponseTopicPublisher.publishFilteredResponse(filteredCognitiveMetadata);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				consumer.ack();

			}
		};

		Subscriber subscriber = null;
		try {
			subscriber = Subscriber.newBuilder(subscription, receiver).build();
			System.out.println("We have build UnfilteredResponseTopicSubscriber here");
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
