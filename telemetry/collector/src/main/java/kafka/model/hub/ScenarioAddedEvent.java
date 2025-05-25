package kafka.model.hub;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@Getter
@Setter
@ToString(callSuper = true)
public class ScenarioAddedEvent extends HubEvent{

    @NotEmpty(message = "Название добавленного сценария не может быть пустым")
    private String name;

    @NotEmpty
    private Collection<ScenarioCondition> conditions;

    @NotEmpty
    private Collection<DeviceAction> actions;

    @Override
    public HubEventType getType(){
        return HubEventType.SCENARIO_ADDED;
    }
}
