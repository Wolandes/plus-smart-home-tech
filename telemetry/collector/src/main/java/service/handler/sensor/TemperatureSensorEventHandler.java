package service.handler.sensor;

import model.sensor.SensorEvent;
import model.sensor.SensorEventType;
import model.sensor.TemperatureSensorEvent;
import ru.yandex.practicum.kafka.telemetry.event.TemperatureSensorAvro;
import service.KafkaEventProducer;

public class TemperatureSensorEventHandler extends BaseSensorEventHandler<TemperatureSensorAvro> {
    public TemperatureSensorEventHandler(KafkaEventProducer producer) {super(producer);}

    @Override
    public SensorEventType getMessageType(){
        return SensorEventType.TEMPERATURE_SENSOR_EVENT;
    }

    @Override
    protected TemperatureSensorAvro mapToAvro(SensorEvent event) {
        TemperatureSensorEvent _event = (TemperatureSensorEvent) event;

        return TemperatureSensorAvro.newBuilder()
                .setTemperatureC(_event.getTemperatureC())
                .setTemperatureF(_event.getTemperatureF())
                .setHubId(_event.getHubId())
                .setId(_event.getId())
                .setTimestamp(_event.getTimestamp())
                .build();
    }
}
