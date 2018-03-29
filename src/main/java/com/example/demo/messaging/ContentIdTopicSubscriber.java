package com.example.demo.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.ContentRequest;
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
public class ContentIdTopicSubscriber {

	private static final String PROJECT_ID = ServiceOptions.getDefaultProjectId();
	
	@Autowired
	CmsUtil cmsUtil;
	
	@Autowired
	FlattenedContentTopicPublisher flattenedContentTopicPublisher;
	

	public void startListener() {

		ProjectSubscriptionName subscription = ProjectSubscriptionName.of(PROJECT_ID, "contentIdsSubscription");

		System.out.println("We are starting ContentIdTopicSubscriber here");

		MessageReceiver receiver = new MessageReceiver() {
			@Override
			public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
				String contentIdObject = null;
				contentIdObject = message.getData().toStringUtf8();
				System.out.println("Message Received in *****ContentIdTopicSubscriber***** : \n" + contentIdObject);
				Gson gson = new Gson();
				FlattenedContent flattenedContentObject = null;
				ContentRequest contentRequest = gson.fromJson(contentIdObject, ContentRequest.class);
				flattenedContentObject = cmsUtil.getFlattenedContentObject(contentRequest.getCmsContentId());
				System.out.println("Flattened Content in *****ContentIdTopicSubscriber***: \n" + flattenedContentObject.toString());
				Gson flattenedGson = new Gson();
				String flattenedGsonString = null;
				flattenedGsonString = flattenedGson.toJson(flattenedContentObject);
				try {
					
					flattenedContentTopicPublisher.publishFlattenedContent(flattenedGsonString);
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
