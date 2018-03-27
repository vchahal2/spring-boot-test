/*package com.example.demo.messaging;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;

public class TestClass {
	{
		System.out.println("This is non static initialization block");
	}
	static {
		System.out.println("This is static block");
	}
	
	  static class MessageReceiverExample implements MessageReceiver {

		    @Override
		    public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
		      System.out.println("This is Static Class Method");
		    }
		  }

	public static void main(String [] args) {
		System.out.println("This is main class");
		TestClass object = new TestClass();
	}
}
*/