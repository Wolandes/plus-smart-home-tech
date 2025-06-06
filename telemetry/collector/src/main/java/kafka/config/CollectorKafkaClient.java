package kafka.config;

import com.google.protobuf.Message;
import org.apache.kafka.clients.producer.Producer;

public interface CollectorKafkaClient {
    Producer<String, Message> getProducer();

    void stop();

    String getTelemetryHubsTopic();

    String getTelemetrySensorsTopic();
}
