package me.pedroeugenio.apicorrida.data.repositories;

import me.pedroeugenio.apicorrida.data.mappers.RunningMapper;
import me.pedroeugenio.apicorrida.presenter.dto.RunningLogDto;
import me.pedroeugenio.apicorrida.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RunningRepository {
    private static final String FILE_NAME = "data.csv";

    private final FileUtils fileUtils;
    private final RunningMapper runningMapper;

    @Autowired
    public RunningRepository(FileUtils fileUtils, RunningMapper runningMapper) {
        this.fileUtils = fileUtils;
        this.runningMapper = runningMapper;
    }

    public List<RunningLogDto> getLogData() {
        return fileUtils.readCsvLines(FILE_NAME).stream().map(runningMapper::mapRunning).collect(Collectors.toList());
    }

}
