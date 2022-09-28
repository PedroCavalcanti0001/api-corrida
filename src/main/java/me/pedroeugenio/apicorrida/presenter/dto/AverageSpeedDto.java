package me.pedroeugenio.apicorrida.presenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AverageSpeedDto {
    private String hero;
    private Float averageSpeed;
}
