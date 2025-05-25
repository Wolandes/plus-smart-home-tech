package service.handler.hub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.hub.HubEvent;
import org.apache.avro.specific.SpecificRecordBase;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import service.HubEventHandler;
import service.KafkaEventProducer;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseHubEventHandler <T extends SpecificRecordBase> implements HubEventHandler {
    protected final KafkaEventProducer producer;

    protected abstract T mapToAvro(HubEvent event);

    @Override
    public void handle(HubEvent event){
        if(!event.getType().equals(getMessageType())){
            throw new IllegalArgumentException("Неизвестный тип события: " + event);
        }

        T payload = mapToAvro(event);

        HubEventAvro eventAvro = HubEventAvro.newBuilder()
                .setHubId(event.getHubId())
                .setTimestamp(event.getTimestamp())
                .setPayload(payload)
                .build();
    }
}
