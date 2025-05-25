package service.handler.sensor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.sensor.SensorEvent;
import org.apache.avro.specific.SpecificRecordBase;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import service.KafkaEventProducer;
import service.handler.SensorEventHandler;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseSensorEventHandler <T extends SpecificRecordBase> implements SensorEventHandler {
    protected  final KafkaEventProducer producer;

    protected abstract T mapToAvro(SensorEvent event);

    @Override
    public void handle(SensorEvent event){
        if (!event.getType().equals(getMessageType())){
            throw new IllegalArgumentException("Неизвестный тип события: " + event);
        }

        T payload = mapToAvro(event);

        SensorEventAvro eventAvro = SensorEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setId(event.getId())
                .setTimestamp(event.getTimestamp())
                .setPayload(payload)
                .build();
    }
}
