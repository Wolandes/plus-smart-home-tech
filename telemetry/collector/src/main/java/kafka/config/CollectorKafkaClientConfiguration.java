package kafka.config;

import lombok.Getter;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.google.protobuf.Message;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;

import java.time.Duration;
import java.util.Properties;

@Configuration
public class CollectorKafkaClientConfiguration {

    @Bean
    @Scope("prototype")
    CollectorKafkaClient getClient() {
        return new CollectorKafkaClient() {

            private Producer<String, Message> producer;

            @Getter
            @Value("${kafka.topics.telemetry-hubs}")
            private String telemetryHubsTopic;

            @Getter
            @Value("${kafka.topics.telemetry-sensors}")
            private String telemetrySensorsTopic;

            @Value("${kafka.bootstrap-servers}")
            private String bootstrapServers;

            @Override
            public Producer<String, Message> getProducer() {
                if (producer == null) {
                    initProducer();
                }
                return producer;
            }

            private void initProducer() {
                Properties config = new Properties();
                config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
                config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
                config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaProtobufSerializer.class);

                producer = new KafkaProducer<>(config);
            }

            @Override
            public void stop() {
                if (producer != null) {
                    producer.flush();
                    producer.close(Duration.ofMillis(10));
                }
            }
        };
    }
}
