package me.pedroeugenio.apicorrida.presenter.dto;

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

}
