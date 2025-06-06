package kafka.mapper;

import kafka.model.sensor.*;
import kafka.model.sensor.SensorEvent;
import org.apache.avro.specific.SpecificRecordBase;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.yandex.practicum.grpc.telemetry.event.*;
import com.google.protobuf.Timestamp;

import java.time.Instant;

@Mapper
public interface SensorEventMapper {
    SensorEventMapper INSTANCE = Mappers.getMapper(SensorEventMapper.class);

    default SensorEventProto toSensorEventProto(SensorEvent event) {

        Timestamp protoTimestamp = Timestamp.newBuilder()
                .setSeconds(event.getTimestamp().getEpochSecond())
                .setNanos(event.getTimestamp().getNano())
                .build();

        SensorEventProto.Builder builder = SensorEventProto.newBuilder()
                .setId(event.getId())
                .setHubId(event.getHubId())
                .setTimestamp(protoTimestamp);

        switch (event.getType()) {
            case CLIMATE_SENSOR_EVENT -> builder.setClimateSensor(toClimateSensorProto((ClimateSensorEvent) event));
            case LIGHT_SENSOR_EVENT -> builder.setLightSensor(toLightSensorProto((LightSensorEvent) event));
            case MOTION_SENSOR_EVENT -> builder.setMotionSensor(toMotionSensorProto((MotionSensorEvent) event));
            case SWITCH_SENSOR_EVENT -> builder.setSwitchSensor(toSwitchSensorProto((SwitchSensorEvent) event));
            case TEMPERATURE_SENSOR_EVENT ->
                    builder.setTemperatureSensor(toTemperatureSensorProto((TemperatureSensorEvent) event));
            default -> throw new IllegalArgumentException("Неизвестный тип: " + event.getType());
        }

        return builder.build();
    }

    ClimateSensorProto toClimateSensorProto(ClimateSensorEvent event);

    LightSensorProto toLightSensorProto(LightSensorEvent event);

    MotionSensorProto toMotionSensorProto(MotionSensorEvent event);

    SwitchSensorProto toSwitchSensorProto(SwitchSensorEvent event);

    TemperatureSensorProto toTemperatureSensorProto(TemperatureSensorEvent event);

    default Timestamp map(Instant instant) {
        if (instant == null) {
            return null;
        }
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }
}
