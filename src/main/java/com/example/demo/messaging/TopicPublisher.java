package com.example.demo.messaging;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.ContentRequest;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.rpc.ApiException;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;


@Component
public class TopicPublisher {

	private static final String PROJECT_ID = ServiceOptions.getDefaultProjectId();
	

	public void publishMessage(String payloadToPublish) throws IOException {

		Publisher publisher = getPublisher();
		try {
			// Create a publisher instance with default settings bound to the topic
			int messageCount = Integer.parseInt("1");

			for (int i = 1; i <= messageCount; i++) {
				// convert message to bytes
				ByteString data = ByteString.copyFromUtf8(payloadToPublish);
				PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

				// schedule a message to be published, messages are automatically batched
				ApiFuture<String> future = publisher.publish(pubsubMessage);

				// add an asynchronous callback to handle success / failure
				ApiFutures.addCallback(future, new ApiFutureCallback<String>() {

					@Override
					public void onFailure(Throwable throwable) {
						if (throwable instanceof ApiException) {
							ApiException apiException = ((ApiException) throwable);
							// details on the API exception
							System.out.println(apiException.getStatusCode().getCode());
							System.out.println(apiException.isRetryable());
						}
						System.out.println("Error publishing message : " + payloadToPublish);
					}

					@Override
					public void onSuccess(String messageId) {
						// Once published, returns server-assigned message ids (unique within the topic)
						System.out.println("Message Published successfully using Topic Publisher");
						System.out.println(messageId);
					}
				});
			}
		} finally {
			/*if (publisher != null) {
				// When finished with the publisher, shutdown to free up resources.
				publisher.shutdown();
			}*/
		}
	}
	
	public void publishContentRequests(ContentRequest[] contentRequests) throws IOException {

		Publisher publisher = getPublisher();
		Gson gson = new Gson();
		String contentRequestsJson = gson.toJson(contentRequests);
		try {
			int messageCount = Integer.parseInt("1");

			for (int i = 1; i <= messageCount; i++) {
				// convert message to bytes
				ByteString data = ByteString.copyFromUtf8(contentRequestsJson);
				PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

				// schedule a message to be published, messages are automatically batched
				ApiFuture<String> future = publisher.publish(pubsubMessage);

				// add an asynchronous callback to handle success / failure
				ApiFutures.addCallback(future, new ApiFutureCallback<String>() {

					@Override
					public void onFailure(Throwable throwable) {
						if (throwable instanceof ApiException) {
							ApiException apiException = ((ApiException) throwable);
							// details on the API exception
							System.out.println(apiException.getStatusCode().getCode());
							System.out.println(apiException.isRetryable());
						}
						System.out.println("Error Publishing Message : " + contentRequestsJson);
					}

					@Override
					public void onSuccess(String messageId) {
						// Once published, returns server-assigned message ids (unique within the topic)
						System.out.println("Message Published successfully");
						System.out.println(messageId);
					}
				});
			}
		} finally {
			/*if (publisher != null) {
				// When finished with the publisher, shutdown to free up resources.
				publisher.shutdown();
			}*/
		}
	}
	
	private Publisher getPublisher() throws IOException {
		
		String topicId = "testTopic";
		ProjectTopicName topicName = ProjectTopicName.of(PROJECT_ID, topicId);
		Publisher publisher =  Publisher.newBuilder(topicName).build();
		return publisher;
	}
}
