package com.ccm.apiengine.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccm.apiengine.pojo.FlattenedContent;
import com.ccm.apiengine.util.WatsonUtil;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.gson.Gson;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;

@Component
public class FlattenedContentTopicSubscriber {

	private static final String PROJECT_ID = ServiceOptions.getDefaultProjectId();
	
	@Autowired
	WatsonUtil watsonUtil;
	
	@Autowired
	UnfilteredResponseTopicPublisher unfilteredResponseTopicPublisher;
	
	public void startListener() {

		ProjectSubscriptionName subscription = ProjectSubscriptionName.of(PROJECT_ID, "flattenedContentSubscription");

		System.out.println("We are starting FlattenedContentTopicSubscriber here");

		MessageReceiver receiver = new MessageReceiver() {
			@Override
			public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {

				String flattenedContent = message.getData().toStringUtf8();
				System.out.println("****FlattenedContentTopicSubscriber****Message Received :  \n" + flattenedContent);
				Gson gson = new Gson();
				String unfilteredCognitiveMetadata = null;
				FlattenedContent flattenedContentPojo = gson.fromJson(flattenedContent, FlattenedContent.class);
				unfilteredCognitiveMetadata = watsonUtil.getCognitiveProfileForText(flattenedContentPojo);
				System.out.println("****FlattenedContentTopicSubscriber****Congnitive Profile Data : \n" + unfilteredCognitiveMetadata);
				try {
					unfilteredResponseTopicPublisher.publishUnfilteredResponse(unfilteredCognitiveMetadata);
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
