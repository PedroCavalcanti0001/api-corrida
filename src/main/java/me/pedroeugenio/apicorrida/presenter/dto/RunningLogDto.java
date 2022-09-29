package me.pedroeugenio.apicorrida.presenter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RunningLogDto {

    private LocalTime time;
    private String hero;
    private Integer lapNumber;
    private Duration lapTime;
    private Float averageSpeedBack;


    /**
     * the lapTime displayed when serializing the object is in seconds
     *
     * @return the lap time in seconds
     */
    @JsonProperty("lapTime")
    public long getLapTime() {
        return lapTime.getSeconds();
    }

}
