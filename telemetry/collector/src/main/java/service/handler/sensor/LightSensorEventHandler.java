package service.handler.sensor;

import model.sensor.LightSensorEvent;
import model.sensor.SensorEvent;
import model.sensor.SensorEventType;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.LightSensorAvro;
import service.KafkaEventProducer;

@Component
public class LightSensorEventHandler extends BaseSensorEventHandler<LightSensorAvro> {
    public LightSensorEventHandler(KafkaEventProducer producer) {super(producer);}

    @Override
    public SensorEventType getMessageType(){
        return SensorEventType.LIGHT_SENSOR_EVENT;
    }

    @Override
    protected LightSensorAvro mapToAvro(SensorEvent event){
        LightSensorEvent _event = (LightSensorEvent) event;

        return LightSensorAvro.newBuilder()
                .setLinkQuality(_event.getLinkQuality())
                .setLuminosity(_event.getLuminosity())
                .build();
    }
}
