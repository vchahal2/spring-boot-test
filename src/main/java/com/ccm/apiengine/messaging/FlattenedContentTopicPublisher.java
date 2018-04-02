package com.ccm.apiengine.messaging;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.rpc.ApiException;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;

@Component
public class FlattenedContentTopicPublisher {

	private static final String PROJECT_ID = ServiceOptions.getDefaultProjectId();

	public void publishFlattenedContent(String flattenedContent) throws Exception {
		Publisher publisher = getPublisher();
		try {
			// convert message to bytes
			ByteString data = ByteString.copyFromUtf8(flattenedContent);
			PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

			
			//Map<String, String> mapObject = new HashMap<String, String>();
			//PubsubMessage pubsubMessage =  PubsubMessage.newBuilder().putAllAttributes(flattenedContent.pojoToMap()).build();
			
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
					System.out.println("Error Publishing Message");
				}

				@Override
				public void onSuccess(String messageId) {
					// Once published, returns server-assigned message ids (unique within the topic)
					System.out.println("***FlattenedContentTopicPublisher****Message with MessageId: " + messageId
							+ " published successfully to FlattenedContent Topic");
					// System.out.println(messageId);
				}
			});

		} finally {
			if (publisher != null) {
				// When finished with the publisher, shutdown to free up resources.
				publisher.shutdown();
			}
		}
	}

	private Publisher getPublisher() throws IOException {

		String topicId = "flattenedContentTopic";
		ProjectTopicName topicName = ProjectTopicName.of(PROJECT_ID, topicId);
		Publisher publisher = Publisher.newBuilder(topicName).build();
		return publisher;
	}

}
