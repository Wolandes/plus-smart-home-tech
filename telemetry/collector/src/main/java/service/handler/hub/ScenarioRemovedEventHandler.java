package service.handler.hub;

import model.hub.HubEvent;
import model.hub.HubEventType;
import model.hub.ScenarioRemovedEvent;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioRemovedEventAvro;
import service.KafkaEventProducer;

@Component
public class ScenarioRemovedEventHandler extends BaseHubEventHandler<ScenarioRemovedEventAvro> {
    public ScenarioRemovedEventHandler(KafkaEventProducer producer) {super(producer);}

    @Override
    public HubEventType getMessageType(){
        return HubEventType.SCENARIO_REMOVED_EVENT;
    }

    @Override
    public ScenarioRemovedEventAvro mapToAvro(HubEvent event){
        ScenarioRemovedEvent _event = (ScenarioRemovedEvent) event;

        return ScenarioRemovedEventAvro.newBuilder()
                .setName(_event.getName())
                .build();
    }
}
