    package com.example.kafkaproducermicroservice.Config;

    import com.example.kafkaproducermicroservice.Entities.Event;
    import org.apache.kafka.clients.CommonClientConfigs;
    import org.apache.kafka.clients.producer.ProducerConfig;
    import org.apache.kafka.common.config.SaslConfigs;
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

    @Configuration
    public class KafkaConfiguration {

        @Value("${spring.kafka.producer.bootstrap-servers}")
        private String bootstrapServers;

        @Value("${spring.kafka.security.protocol}")
        private String securityProtocol;

        @Value("${spring.kafka.properties.sasl.mechanism}")
        private String saslMechanism;

        @Value("${spring.kafka.properties.sasl.jaas.config}")
        private String saslJaasConfig;

        @Bean
        public ProducerFactory<String, Event> producerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            configProps.put(SaslConfigs.SASL_MECHANISM, saslMechanism);
            configProps.put(SaslConfigs.SASL_JAAS_CONFIG, saslJaasConfig);
            configProps.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, securityProtocol);
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean
        public KafkaTemplate<String, Event> kafkaTemplate() {
            return new KafkaTemplate<>(producerFactory());
        }
    }
