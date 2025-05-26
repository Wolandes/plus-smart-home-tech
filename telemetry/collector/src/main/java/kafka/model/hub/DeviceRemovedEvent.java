package kafka.model.hub;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class DeviceRemovedEvent extends HubEvent {

    @NotEmpty(message = "Идентификатор удаленного устройства не может быть пустым")
    private String id;

    @Override
    public HubEventType getType(){
        return HubEventType.DEVICE_REMOVED;
    }
}
