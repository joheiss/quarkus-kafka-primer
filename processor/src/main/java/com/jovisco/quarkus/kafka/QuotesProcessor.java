package com.jovisco.quarkus.kafka;

import java.util.Random;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import com.jovisco.quarkus.kafka.model.Quote;

import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuotesProcessor {

    private Random random = new Random();

    @Incoming("requests")
    @Outgoing("quotes")
    @Blocking
    public Quote process(String quoteRequest) throws InterruptedException {
        
        // simulate some work ...
        Thread.sleep(200);

        return new Quote(quoteRequest, random.nextInt(100));
    }

}
