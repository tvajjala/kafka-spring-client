package com.barclays.kafka.config;


import com.barclays.kafka.model.PaymentDetails;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * For consuming messages, we need to configure {@link ConsumerFactory}
 * <p>
 * and {@link org.springframework.kafka.config.KafkaListenerContainerFactory}. Once these beans are available POJO based consumers
 * can be configured using @{@link org.springframework.kafka.annotation.KafkaListener} annotation
 */
//@EnableKafka
@Configuration
public class KafkaConsumerConfig {


    @Value("${kafka.broker.address}")
    private String kafkaBrokerAddress;

    private static final String GROUP_ID = "FRAUD";

    @Bean
    public ConsumerFactory<String, PaymentDetails> consumerFactory() {
        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokerAddress);
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);

        return new DefaultKafkaConsumerFactory<>(configProperties,
                new StringDeserializer(),
                new JsonDeserializer<>(PaymentDetails.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentDetails> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, PaymentDetails> factory
                = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
