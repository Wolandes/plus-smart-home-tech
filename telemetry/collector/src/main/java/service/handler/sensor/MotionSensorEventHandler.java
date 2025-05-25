package service.handler.sensor;

import model.sensor.MotionSensorEvent;
import model.sensor.SensorEvent;
import model.sensor.SensorEventType;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.MotionSensorAvro;
import service.KafkaEventProducer;

@Component
public class MotionSensorEventHandler extends BaseSensorEventHandler<MotionSensorAvro> {
    public MotionSensorEventHandler(KafkaEventProducer producer) {super(producer);}

    @Override
    public SensorEventType getMessageType(){
        return SensorEventType.MOTION_SENSOR_EVENT;
    }

    @Override
    protected MotionSensorAvro mapToAvro(SensorEvent event){
        MotionSensorEvent _event = (MotionSensorEvent) event;

        return MotionSensorAvro.newBuilder()
                .setLinkQuality(_event.getLinkQuality())
                .setMotion(_event.isMotion())
                .setVoltage(_event.getLinkQuality())
                .build();
    }
}
