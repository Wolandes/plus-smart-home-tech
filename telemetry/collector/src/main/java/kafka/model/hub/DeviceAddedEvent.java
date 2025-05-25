package kafka.model.hub;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class DeviceAddedEvent extends HubEvent{

    @NotEmpty(message = "Идентификатор добавленного устройства не может быть пустым")
    private String id;

    @NotNull(message = "Тип устройства не может быть равен null")
    private DeviceType deviceType;

    @Override
    public HubEventType getType(){
        return HubEventType.DEVICE_ADDED;
    }
}
