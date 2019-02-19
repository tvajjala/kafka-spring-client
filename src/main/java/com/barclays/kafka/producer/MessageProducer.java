package com.barclays.kafka.producer;

import com.barclays.kafka.model.PaymentDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import static java.lang.System.currentTimeMillis;

/**
 * This spring component sends messages to the given Topic for every 5 seconds.
 * The response is Blocking object on which we added callback function to read the status of our message
 */
@Component
public class MessageProducer implements CommandLineRunner {


    @Autowired
    KafkaTemplate kafkaTemplate;

    @Value("${message.topic.payments}")
    String paymentMessageTopic;


    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);


    @Override
    public void run(String... args) throws InterruptedException {

        do {
            sendMessage(new PaymentDetails(currentTimeMillis(), "12345", "Thiru", 1234.0));
            Thread.sleep(5000);
        } while (true);


    }


    public void sendMessage(PaymentDetails message) {

        ListenableFuture<SendResult<String, PaymentDetails>> future = kafkaTemplate.send(paymentMessageTopic, message);


        future.addCallback(new ListenableFutureCallback<SendResult<String, PaymentDetails>>() {


            @Override
            public void onFailure(Throwable ex) {
                LOGGER.warn("Message Sending Failed {} ", ex);
            }

            @Override
            public void onSuccess(SendResult<String, PaymentDetails> result) {

                LOGGER.info("Message [{}] sent to topic [{}]", result.getProducerRecord().value(), result.getRecordMetadata().topic());
            }
        });
    }
}
