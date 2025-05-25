package service.handler.hub;

import model.hub.DeviceAddedEvent;
import model.hub.HubEvent;
import model.hub.HubEventType;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.DeviceAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceTypeAvro;
import service.KafkaEventProducer;

@Component
public class DeviceAddedEventHandler extends BaseHubEventHandler<DeviceAddedEventAvro> {
    public DeviceAddedEventHandler(KafkaEventProducer producer) {super(producer);}

    @Override
    public HubEventType getMessageType(){
        return HubEventType.DEVICE_ADDED_EVENT;
    }

    @Override
    public DeviceAddedEventAvro mapToAvro(HubEvent event){
        DeviceAddedEvent _event = (DeviceAddedEvent) event;

        return DeviceAddedEventAvro.newBuilder()
                .setId(_event.getId())
                .setType(DeviceTypeAvro.valueOf(_event.getType().name()))
                .build();
     }
}
