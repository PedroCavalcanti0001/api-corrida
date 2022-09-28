package me.pedroeugenio.apicorrida.data.services;

import me.pedroeugenio.apicorrida.data.repositories.RunningRepository;
import me.pedroeugenio.apicorrida.presenter.dto.AverageSpeedDto;
import me.pedroeugenio.apicorrida.presenter.dto.RunningLogDto;
import me.pedroeugenio.apicorrida.utils.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RunningServiceTest {

    @Mock
    private FileUtils mockFileUtils;
    @Mock
    private RunningRepository mockRunningRepository;

    private RunningService runningServiceUnderTest;

    @BeforeEach
    void setUp() {
        runningServiceUnderTest = new RunningService(mockFileUtils, mockRunningRepository);
    }

    @Test
    void testGetAllLaps() {
        // Setup
        final List<RunningLogDto> expectedResult = Arrays.asList(
                new RunningLogDto(LocalTime.of(12, 0, 0), "hero", 0, Duration.ofDays(0L), 0.0f));

        // Configure RunningRepository.getLogData(...).
        final List<RunningLogDto> runningLogDtos = Arrays.asList(
                new RunningLogDto(LocalTime.of(12, 0, 0), "hero", 0, Duration.ofDays(0L), 0.0f));
        when(mockRunningRepository.getLogData()).thenReturn(runningLogDtos);

        // Run the test
        final List<RunningLogDto> result = runningServiceUnderTest.getAllLaps();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllLaps_RunningRepositoryReturnsNoItems() {
        // Setup
        when(mockRunningRepository.getLogData()).thenReturn(Collections.emptyList());

        // Run the test
        final List<RunningLogDto> result = runningServiceUnderTest.getAllLaps();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetLastLaps() {
        // Setup
        final List<RunningLogDto> expectedResult = Arrays.asList(
                new RunningLogDto(LocalTime.of(12, 0, 0), "hero", 4, Duration.ofDays(0L), 0.0f));

        // Configure RunningRepository.getLogData(...).
        final List<RunningLogDto> runningLogDtos = Arrays.asList(
                new RunningLogDto(LocalTime.of(12, 0, 0), "hero", 4, Duration.ofDays(0L), 0.0f));
        when(mockRunningRepository.getLogData()).thenReturn(runningLogDtos);

        // Run the test
        final List<RunningLogDto> result = runningServiceUnderTest.getLastLaps();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetLastLaps_RunningRepositoryReturnsNoItems() {
        // Setup
        when(mockRunningRepository.getLogData()).thenReturn(Collections.emptyList());

        // Run the test
        final List<RunningLogDto> result = runningServiceUnderTest.getLastLaps();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetBestLapOfTheRace() {
        // Setup
        final RunningLogDto expectedResult = new RunningLogDto(LocalTime.of(12, 0, 0), "hero", 0, Duration.ofDays(0L),
                0.0f);

        // Configure RunningRepository.getLogData(...).
        final List<RunningLogDto> runningLogDtos = Arrays.asList(
                new RunningLogDto(LocalTime.of(12, 0, 0), "hero", 0, Duration.ofDays(0L), 0.0f));
        when(mockRunningRepository.getLogData()).thenReturn(runningLogDtos);

        // Run the test
        final RunningLogDto result = runningServiceUnderTest.getBestLapOfTheRace();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetBestLapOfTheRace_RunningRepositoryReturnsNoItems() {
        // Setup
        final RunningLogDto expectedResult = new RunningLogDto(LocalTime.of(12, 0, 0), "hero", 0, Duration.ofDays(0L),
                0.0f);
        final RunningLogDto value = new RunningLogDto(LocalTime.of(12, 0, 0), "hero", 0, Duration.ofDays(0L),
                0.0f);
        when(mockRunningRepository.getLogData()).thenReturn(Collections.singletonList(value));

        // Run the test
        final RunningLogDto result = runningServiceUnderTest.getBestLapOfTheRace();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetWinner() {
        // Setup
        final RunningLogDto expectedResult = new RunningLogDto(LocalTime.of(12, 0, 0), "hero", 4, Duration.ofDays(0L),
                0.0f);

        // Configure RunningRepository.getLogData(...).
        final List<RunningLogDto> runningLogDtos = Arrays.asList(
                new RunningLogDto(LocalTime.of(12, 0, 0), "hero", 4, Duration.ofDays(0L), 0.0f));
        when(mockRunningRepository.getLogData()).thenReturn(runningLogDtos);

        // Run the test
        final RunningLogDto result = runningServiceUnderTest.getWinner();
        System.out.println(result);
        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetWinner_RunningRepositoryReturnsNoItems() {
        // Setup
        when(mockRunningRepository.getLogData()).thenReturn(Collections.emptyList());

        // Run the test
      assertThrows(IllegalArgumentException.class, () -> {
            runningServiceUnderTest.getWinner();
        });
    }

    @Test
    void testGetAverageSpeed() {
        // Setup
        final List<AverageSpeedDto> expectedResult = Arrays.asList(new AverageSpeedDto("hero", 0.0f));

        // Configure RunningRepository.getLogData(...).
        final List<RunningLogDto> runningLogDtos = Arrays.asList(
                new RunningLogDto(LocalTime.of(12, 0, 0), "hero", 4, Duration.ofDays(0L), 0.0f));
        when(mockRunningRepository.getLogData()).thenReturn(runningLogDtos);

        // Run the test
        final List<AverageSpeedDto> result = runningServiceUnderTest.getAverageSpeed();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAverageSpeed_RunningRepositoryReturnsNoItems() {
        // Setup
        final List<AverageSpeedDto> expectedResult = Collections.emptyList();
        when(mockRunningRepository.getLogData()).thenReturn(Collections.emptyList());

        // Run the test
        final List<AverageSpeedDto> result = runningServiceUnderTest.getAverageSpeed();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
