package me.pedroeugenio.apicorrida.data.mappers;

import me.pedroeugenio.apicorrida.presenter.dto.RunningLogDto;
import me.pedroeugenio.apicorrida.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RunningMapper {

    private final TimeUtils timeUtils;

    @Autowired
    public RunningMapper(TimeUtils timeUtils) {
        this.timeUtils = timeUtils;
    }

    public RunningLogDto mapRunning(String[] vect) {
        RunningLogDto runningLogDto = new RunningLogDto();
        runningLogDto.setTime(timeUtils.parseTimeInStr(vect[0]));
        runningLogDto.setHero(vect[1]);
        runningLogDto.setLapNumber(Integer.valueOf(vect[2]));
        runningLogDto.setLapTime(Duration.ofSeconds(Integer.parseInt(vect[3].split(":")[0]) * 60L
                + Integer.parseInt(vect[3].split(":")[1].split("\\.")[0])));
        runningLogDto.setAverageSpeedBack(Float.valueOf(vect[4].replace(",", ".")));
        return runningLogDto;
    }


}
