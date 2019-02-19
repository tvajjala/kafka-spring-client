package com.barclays.kafka.config;

import com.barclays.kafka.model.PaymentDetails;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * This class lets you allow to create {@link KafkaTemplate} instance,
 * which is used to send messages to Topic.
 * <p>
 * {@link KafkaTemplate} requires {@link ProducerFactory} instance
 * <p>
 * {@link org.apache.kafka.clients.producer.Producer} instance and {@link KafkaTemplate} are thread-safe.
 *
 * @author ThirupathiReddy Vajjala
 */
@Configuration
public class KafkaTemplateConfig {


    @Value("${kafka.broker.address}")
    private String kafkaBrokerAddress;


    @Bean
    public ProducerFactory<String, PaymentDetails> producerFactory() {
        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokerAddress);
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProperties.put(ProducerConfig.ACKS_CONFIG, "all");
        configProperties.put(ProducerConfig.RETRIES_CONFIG, 0);
        return new DefaultKafkaProducerFactory<>(configProperties);
    }


    /**
     * KafkaTemplate used to produce messages into topics
     *
     * @param producerFactory producerFactory instance
     * @return kafkaTemplate {@link KafkaTemplate}
     */
    @Bean
    public KafkaTemplate<String, PaymentDetails> kafkaTemplate(ProducerFactory producerFactory) {

        return new KafkaTemplate<>(producerFactory);
    }


}
