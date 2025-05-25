package service;

import config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import model.hub.HubEvent;
import model.sensor.SensorEvent;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.EnumMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class KafkaEventProducer {

    private final KafkaProducer<String, SpecificRecordBase> producer;
    private final Map<KafkaConfig.TopicType, String> topics = new EnumMap<>(KafkaConfig.TopicType.class);

    public KafkaEventProducer(KafkaConfig kafkaConfig) {
        KafkaConfig.ProducerConfig config = kafkaConfig.getProducerConfig();
        Properties props = config.getProperties();
        this.producer = new KafkaProducer<>(props);
        this.topics.putAll(config.getTopics());

        log.info("KafkaEventProducer инициализирован с топиками: {}", topics);
    }

    public void sendSensorEvent(SensorEvent event, SpecificRecordBase payload) {
        send(KafkaConfig.TopicType.SENSOR_EVENTS, event.getId(), payload);
    }

    public void sendHubEvent(HubEvent event, SpecificRecordBase payload) {
        send(KafkaConfig.TopicType.HUBS_EVENTS, event.getHubId(), payload);
    }

    private void send(KafkaConfig.TopicType type, String key, SpecificRecordBase payload) {
        String topic = topics.get(type);
        if (topic == null) {
            log.warn("Топик не найден для типа {}", type);
            return;
        }

        ProducerRecord<String, SpecificRecordBase> record = new ProducerRecord<>(topic, key, payload);
        producer.send(record, (metadata, exception) -> {
            if (exception != null) {
                log.error("Ошибка при отправке события в Kafka: {}", exception.getMessage(), exception);
            } else {
                log.debug("Событие отправлено в Kafka: topic={}, partition={}, offset={}",
                        metadata.topic(), metadata.partition(), metadata.offset());
            }
        });
    }
}
