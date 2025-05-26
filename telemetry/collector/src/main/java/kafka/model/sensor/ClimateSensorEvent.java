package kafka.model.sensor;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class ClimateSensorEvent extends SensorEvent {

    @NotNull(message = "Уровень температуры по шкале Цельсия не может быть равным null")
    private Integer temperatureC;
    @NotNull(message = "Влажность не может быть равным null")
    private Integer humidity;
    @NotNull(message = "Уровень углекислого газа не может быть равным null")
    private Integer co2Level;

    @Override
    public SensorEventType getType() {
        return SensorEventType.CLIMATE_SENSOR_EVENT;
    }
}
