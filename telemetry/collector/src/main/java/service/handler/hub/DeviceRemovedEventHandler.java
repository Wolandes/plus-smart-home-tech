package service.handler.hub;

import model.hub.DeviceRemovedEvent;
import model.hub.HubEvent;
import model.hub.HubEventType;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.DeviceRemovedEventAvro;
import service.KafkaEventProducer;

@Component
public class DeviceRemovedEventHandler extends BaseHubEventHandler<DeviceRemovedEventAvro> {
    public DeviceRemovedEventHandler(KafkaEventProducer producer) {super(producer);}

    @Override
    public HubEventType getMessageType(){
        return HubEventType.DEVICE_REMOVED_EVENT;
    }

    @Override
    public DeviceRemovedEventAvro mapToAvro(HubEvent event){
        DeviceRemovedEvent _event = (DeviceRemovedEvent) event;

        return DeviceRemovedEventAvro.newBuilder()
                .setId(_event.getId())
                .build();
    }
}
