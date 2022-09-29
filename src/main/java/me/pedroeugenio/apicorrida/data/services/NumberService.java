package me.pedroeugenio.apicorrida.data.services;

import me.pedroeugenio.apicorrida.data.mappers.NumberMapper;
import me.pedroeugenio.apicorrida.data.repositories.NumberRepository;
import me.pedroeugenio.apicorrida.presenter.dto.NumberDto;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Number service.
 */
@Service
public class NumberService {

    private final NumberRepository numberRepository;
    private final NumberMapper numberMapper;

    /**
     * Instantiates a new Number service.
     *
     * @param numberRepository the number repository
     * @param numberMapper     the number mapper
     */
    @Autowired
    NumberService(NumberRepository numberRepository, NumberMapper numberMapper) {
        this.numberRepository = numberRepository;
        this.numberMapper = numberMapper;
    }

    /**
     * It loops through the values of all array values and checks their occurs
     *
     * @param an Vector to be verified
     * @return The list of occurrences for each value of the passed vector
     */
    public List<NumberDto> searchOccurrences(Integer[] an) {
        int[][] matriz = numberRepository.GetBiArray();
        List<ImmutablePair<Integer, Integer>> collect = Arrays.stream(an)
                .map(e -> { int optionalInteger = Arrays.stream(matriz).mapToInt(v -> (int) Arrays.stream(v)
                    .filter(b -> b == e).count()).sum();
            return new ImmutablePair<>(e, optionalInteger);
        }).collect(Collectors.toList());
        return numberMapper.mapNumbers(collect);
    }
}
