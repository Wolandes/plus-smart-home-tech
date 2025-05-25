package service.handler.sensor;

import model.sensor.SensorEvent;
import model.sensor.SensorEventType;
import model.sensor.SwitchSensorEvent;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.SwitchSensorAvro;
import service.KafkaEventProducer;

@Component
public class SwitchSensorEventHandler extends BaseSensorEventHandler<SwitchSensorAvro> {
    public SwitchSensorEventHandler (KafkaEventProducer producer) {super(producer);}

    @Override
    public SensorEventType getMessageType(){
        return SensorEventType.SWITCH_SENSOR_EVENT;
    }

    @Override
    protected SwitchSensorAvro mapToAvro(SensorEvent event) {
        SwitchSensorEvent _event = (SwitchSensorEvent) event;

        return SwitchSensorAvro.newBuilder()
                .setState(_event.isState())
                .build();
    }
}
