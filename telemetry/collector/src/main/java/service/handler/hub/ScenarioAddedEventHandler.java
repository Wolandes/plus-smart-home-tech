package service.handler.hub;

import model.hub.HubEvent;
import model.hub.HubEventType;
import model.hub.ScenarioAddedEvent;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.*;
import service.KafkaEventProducer;

import java.util.stream.Collectors;

@Component
public class ScenarioAddedEventHandler extends BaseHubEventHandler<ScenarioAddedEventAvro> {
    public ScenarioAddedEventHandler(KafkaEventProducer producer) {super(producer);}

    @Override
    public HubEventType getMessageType(){
        return HubEventType.SCENARIO_ADDED_EVENT;
    }

    @Override
    public ScenarioAddedEventAvro mapToAvro(HubEvent event){
        ScenarioAddedEvent _event = (ScenarioAddedEvent) event;

        return ScenarioAddedEventAvro.newBuilder()
                .setName(_event.getName())
                .setActions(_event.getActions().stream()
                        .map(action -> DeviceActionAvro.newBuilder()
                                .setSensorId(action.getSensorId())
                                .setType(ActionTypeAvro.valueOf(action.getType().name()))
                                .setValue(action.getValue() != null ? action.getValue() : null)
                                .build())
                        .collect(Collectors.toList()))
                .setConditions(_event.getConditions().stream()
                        .map(cond -> ScenarioConditionAvro.newBuilder()
                                .setSensorId(cond.getSensorId())
                                .setType(ConditionTypeAvro.valueOf(cond.getType().name()))
                                .setOperation(ConditionOperationAvro.valueOf(cond.getOperation().name()))
                                .setValue(cond.getValue())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
