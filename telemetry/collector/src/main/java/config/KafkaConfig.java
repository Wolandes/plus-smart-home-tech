package config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.EnumMap;
import java.util.Map;
import java.util.Properties;

@Getter
@Setter
@ToString
@ConfigurationProperties("collector.kafka")
public class KafkaConfig {
    ProducerConfig producerConfig;

    @Getter
    public static class ProducerConfig {
        private final Properties properties;
        private final EnumMap<TopicType, String> topics = new EnumMap<>(TopicType.class);

        public ProducerConfig(Properties properties, Map<String, String> topics) {
            this.properties = properties;
            for (Map.Entry<String, String> entry : topics.entrySet()) {
                this.topics.put(TopicType.from(entry.getKey()), entry.getValue());
            }
        }
    }

    public enum TopicType {
        SENSOR_EVENTS,
        HUBS_EVENTS;

        public static TopicType from(String type) {
            return switch (type.toLowerCase().replace("-", "_")) {
                case "sensor_events" -> SENSOR_EVENTS;
                case "hubs_events" -> HUBS_EVENTS;
                default -> throw new IllegalArgumentException("Неизвестный топик: " + type);
            };
        }
    }
}
