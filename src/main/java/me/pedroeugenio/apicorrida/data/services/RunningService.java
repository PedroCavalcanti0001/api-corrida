package me.pedroeugenio.apicorrida.data.services;

import me.pedroeugenio.apicorrida.data.repositories.RunningRepository;
import me.pedroeugenio.apicorrida.presenter.dto.AverageSpeedDto;
import me.pedroeugenio.apicorrida.presenter.dto.RunningLogDto;
import me.pedroeugenio.apicorrida.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The type Running service.
 */
@Service
public class RunningService {

    private FileUtils fileUtils;
    private RunningRepository runningRepository;

    /**
     * Instantiates a new Running service.
     *
     * @param fileUtils         the file utils
     * @param runningRepository the running repository
     */
    @Autowired
    public RunningService(FileUtils fileUtils, RunningRepository runningRepository) {
        this.fileUtils = fileUtils;
        this.runningRepository = runningRepository;
    }

    /**
     * Gets all laps.
     *
     * @return the all laps
     */
    public List<RunningLogDto> getAllLaps() {
        return runningRepository.getLogData();
    }

    /**
     * Gets last laps.
     *
     * @return the last laps
     */
    public List<RunningLogDto> getLastLaps() {
        return lastLaps();
    }

    /**
     * Gets best lap of the race.
     *
     * @return the best lap of the race
     */
    public RunningLogDto getBestLapOfTheRace() {
        List<RunningLogDto> laps = runningRepository.getLogData();
        laps.sort(Comparator.comparing(RunningLogDto::getLapTime));
        return laps.get(0);
    }

    /**
     * Gets winner.
     *
     * @return the winner
     */
    public RunningLogDto getWinner() {
        return lastLaps().get(0);
    }

    /**
     * Gets average speed.
     *
     * @return the average speed
     */
    public List<AverageSpeedDto> getAverageSpeed() {
        List<RunningLogDto> laps = runningRepository.getLogData();
        return lastLaps().stream().map(l -> {
            List<RunningLogDto> heroLaps = laps.stream().filter(e -> e.getHero().equals(l.getHero())).collect(Collectors.toList());
            double total = heroLaps.stream().mapToDouble(RunningLogDto::getAverageSpeedBack).sum();
            int amt = heroLaps.size();
            return new AverageSpeedDto(l.getHero(), (float) total / amt);
        }).collect(Collectors.toList());
    }

    private List<RunningLogDto> lastLaps() {
        return runningRepository.getLogData().stream().filter(e -> e.getLapNumber() == 4)
                .sorted(Comparator.comparing(RunningLogDto::getLapTime)).collect(Collectors.toList());
    }
}
