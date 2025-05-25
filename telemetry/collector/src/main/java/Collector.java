import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration.class
})
@ConfigurationPropertiesScan
public class Collector {
    public static void main(String[] args) {
        SpringApplication.run(Collector.class, args);
    }
}
