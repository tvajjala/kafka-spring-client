package com.barclays.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import static java.util.Collections.singletonMap;
import static org.apache.kafka.clients.admin.AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG;

/**
 * This configuration lets you to create Topic in the specified broker
 *
 * @author ThirupathiReddy Vajjala
 */
@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.broker.address}")
    private String kafkaBrokerAddress;


    /**
     * An admin that delegates to an {@link org.apache.kafka.clients.admin.AdminClient} to create topics defined
     * in the application context.
     *
     * @return KafkaAdmin {@link KafkaAdmin}
     */
    @Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(singletonMap(BOOTSTRAP_SERVERS_CONFIG, kafkaBrokerAddress));
    }


    /**
     * Create Topic on the above specified broker instance
     *
     * @param topicName topicName from configuration
     * @return topicInstance
     */
    @Bean
    public NewTopic paymentDetailsTopic(@Value("${message.topic.payments}") String topicName) {

        return new NewTopic(topicName, 1, (short) 1);
    }


}

