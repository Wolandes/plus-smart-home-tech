package kafka.config;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.Producer;

public interface CollectorKafkaClient {
    Producer<String, SpecificRecordBase> getProducer();

    void stop();

    String getTelemetryHubsTopic();

    String getTelemetrySensorsTopic();
}
