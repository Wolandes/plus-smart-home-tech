package ru.yandex.practicum.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.yandex.practicum.config.KafkaConsumerSettings;

@Getter
@Setter
@ConfigurationProperties(prefix = "kafka.config")
public class KafkaConsumerProperties {
    private String bootstrapServers;
    private KafkaConsumerSettings hubConsumer;
    private KafkaConsumerSettings snapshotConsumer;
    private String hubEventsTopic;
    private String sensorSnapshotsTopic;
}