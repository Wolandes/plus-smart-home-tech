package kafka.mapper;

import kafka.model.hub.*;
import kafka.model.hub.HubEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.yandex.practicum.grpc.telemetry.event.*;
import com.google.protobuf.Timestamp;

@Mapper
public interface HubEventMapper {
    HubEventMapper INSTANCE = Mappers.getMapper(HubEventMapper.class);

    default HubEventProto toHubEventProto(HubEvent event) {

        Timestamp protoTimestamp = Timestamp.newBuilder()
                .setSeconds(event.getTimestamp().getEpochSecond())
                .setNanos(event.getTimestamp().getNano())
                .build();

        HubEventProto.Builder builder = HubEventProto.newBuilder()
                .setHubId(event.getHubId())
                .setTimestamp(protoTimestamp);

        switch (event.getType()) {
            case DEVICE_ADDED -> builder.setDeviceAddedEvent(toDeviceAddedEventProto((DeviceAddedEvent) event));
            case DEVICE_REMOVED -> builder.setDeviceRemovedEvent(toDeviceRemovedEventProto((DeviceRemovedEvent) event));
            case SCENARIO_ADDED -> builder.setScenarioAddedEvent(toScenarioAddedEventProto((ScenarioAddedEvent) event));
            case SCENARIO_REMOVED ->
                    builder.setScenarioRemovedEvent(toScenarioRemovedEventProto((ScenarioRemovedEvent) event));
            default -> throw new IllegalArgumentException("Неизвестный тип : " + event.getType());
        }
        return builder.build();
    }


    @Mapping(source = "deviceType", target = "type")
    DeviceAddedEventProto toDeviceAddedEventProto(DeviceAddedEvent event);

    DeviceRemovedEventProto toDeviceRemovedEventProto(DeviceRemovedEvent event);

    ScenarioAddedEventProto toScenarioAddedEventProto(ScenarioAddedEvent event);

    ScenarioRemovedEventProto toScenarioRemovedEventProto(ScenarioRemovedEvent event);
}