package diary.weather.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String weather;

    private String icon;

    private Double temperature;

    private String text;

    private LocalDate date;

    public void setDateWeather(DateWeather dateWeather) {
        this.date = dateWeather.getDate();
        this.weather = dateWeather.getWeather();
        this.icon = dateWeather.getIcon();
        this.temperature = dateWeather.getTemperature();

    }
}
