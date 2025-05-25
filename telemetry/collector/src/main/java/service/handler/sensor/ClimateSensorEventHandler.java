package service.handler.sensor;

import model.sensor.ClimateSensorEvent;
import model.sensor.SensorEvent;
import model.sensor.SensorEventType;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.ClimateSensorAvro;
import service.KafkaEventProducer;

@Component
public class ClimateSensorEventHandler extends BaseSensorEventHandler<ClimateSensorAvro> {
    public ClimateSensorEventHandler(KafkaEventProducer producer) {
        super(producer);
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.CLIMATE_SENSOR_EVENT;
    }

    @Override
    protected ClimateSensorAvro mapToAvro(SensorEvent event) {
        ClimateSensorEvent _event = (ClimateSensorEvent) event;

        return ClimateSensorAvro.newBuilder()
                .setCo2Level(_event.getCo2Level())
                .setHumidity(_event.getHumidity())
                .setTemperatureC(_event.getTemperatureC())
                .build();
    }
}
