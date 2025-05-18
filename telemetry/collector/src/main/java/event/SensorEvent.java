package event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.SensorEventType;

import java.time.Instant;

@Getter
@Setter
@ToString
public abstract class SensorEvent {
    private String id;
    private String hubId;
    private Instant timestamp = Instant.now();

    // абстрактный метод, который должен быть определён в конкретных реализациях
    public abstract SensorEventType getType();
}
