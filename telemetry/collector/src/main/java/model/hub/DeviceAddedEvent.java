package model.hub;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class DeviceAddedEvent extends HubEvent{

    @NotBlank
    private String id;

    @NotNull
    private DeviceType type;

    @Override
    public HubEventType getType(){
        return HubEventType.DEVICE_ADDED_EVENT;
    }
}
