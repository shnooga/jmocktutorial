package com.oogie;

import junit.framework.TestCase;
import org.jmock.Expectations;
import org.jmock.Mockery;

public class PublisherTest extends TestCase {
    Mockery context = new Mockery();

    public void testOneSubscriberReceivesAMessage() {
        // set up
        final Subscriber subscriber = context.mock(Subscriber.class);

        Publisher publisher = new Publisher();
        publisher.add(subscriber);
        
        final String message = "message";
        
        // expectations
        context.checking(new Expectations() {{
            oneOf (subscriber).receive(message);
        }});

        // execute
        publisher.publish(message);
        
        // verify
        context.assertIsSatisfied();
    }
}