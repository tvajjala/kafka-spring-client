package com.barclays.kafka.consumer;

import com.barclays.kafka.model.PaymentDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {


    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    @KafkaListener(topics = "payments", groupId = "FRAUD")
    public void receivePaymentDetails(PaymentDetails message) {

        LOGGER.info("Received Message {} ", message);

    }

}
