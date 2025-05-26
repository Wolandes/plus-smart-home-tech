package kafka.controller;

import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;
import kafka.config.CollectorKafkaClient;
import kafka.mapper.HubEventMapper;
import kafka.mapper.SensorEventMapper;
import kafka.model.hub.HubEvent;
import kafka.model.sensor.SensorEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/events")
@RequiredArgsConstructor
@RestController
public class EventController {
    private final CollectorKafkaClient kafkaClient;

    @PostMapping("/hubs")
    public void collectHubEvent(@RequestBody @Valid HubEvent event) {
        kafkaClient.getProducer().send(new ProducerRecord<>(kafkaClient.getTelemetryHubsTopic(), HubEventMapper.INSTANCE.toHubEventAvro(event)));
    }

    @PostMapping("/sensors")
    public void collectSensorEvent(@RequestBody @Valid SensorEvent event) {
        kafkaClient.getProducer().send(new ProducerRecord<>(kafkaClient.getTelemetrySensorsTopic(), SensorEventMapper.INSTANCE.toSensorEventAvro(event)));
    }

    @PreDestroy
    public void preDestroy() {
        kafkaClient.stop();
    }
}
