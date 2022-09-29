package me.pedroeugenio.apicorrida.data.services;

import me.pedroeugenio.apicorrida.data.mappers.NumberMapper;
import me.pedroeugenio.apicorrida.data.repositories.NumberRepository;
import me.pedroeugenio.apicorrida.presenter.dto.NumberDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NumberServiceTest {

    @Mock
    private NumberRepository numberRepository;
    @Mock
    private NumberMapper numberMapper;

    private NumberService numberServiceUnderTest;

    @BeforeEach
    void setUp() {
        numberServiceUnderTest = new NumberService(numberRepository, numberMapper);
    }

    @Test
    void testSearchOccurrences() {
        // Setup
        final List<NumberDto> expectedResult = Arrays.asList(new NumberDto(0, 1));
        when(numberRepository.GetBiArray()).thenReturn(new int[][]{{0}});
        when(numberMapper.mapNumbers(new ArrayList<>())).thenReturn(Arrays.asList(new NumberDto(0, 1)));

        // Run the test
        final List<NumberDto> result = numberServiceUnderTest.searchOccurrences(new Integer[]{});

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSearchOccurrences_NumberRepositoryReturnsNoItems() {
        // Setup
        when(numberRepository.GetBiArray()).thenReturn(new int[][]{});
        when(numberMapper.mapNumbers(new ArrayList<>())).thenReturn(Collections.emptyList());

        // Run the test
        final List<NumberDto> result = numberServiceUnderTest.searchOccurrences(new Integer[]{});

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSearchOccurrences_NumberMapperReturnsNoItems() {
        // Setup
        when(numberRepository.GetBiArray()).thenReturn(new int[][]{});
        when(numberMapper.mapNumbers(new ArrayList<>())).thenReturn(Collections.emptyList());

        // Run the test
        final List<NumberDto> result = numberServiceUnderTest.searchOccurrences(new Integer[]{});

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
