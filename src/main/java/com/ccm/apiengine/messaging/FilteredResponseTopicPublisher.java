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
public class FilteredResponseTopicPublisher {

	private static final String PROJECT_ID = ServiceOptions.getDefaultProjectId();
	
	public void publishFilteredResponse(String filteredResponse) throws Exception {

		Publisher publisher = getPublisher();
		try {
				// convert message to bytes
				ByteString data = ByteString.copyFromUtf8(filteredResponse);
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
						System.out.println("Error Publishing Message");
					}

					@Override
					public void onSuccess(String messageId) {
						System.out.println("FilteredMetadataResponse with MessageId: " + messageId +  " published successfully");
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
		
		String topicId = "filteredResponseTopic";
		ProjectTopicName topicName = ProjectTopicName.of(PROJECT_ID, topicId);
		Publisher publisher =  Publisher.newBuilder(topicName).build();
		return publisher;
	}
}
