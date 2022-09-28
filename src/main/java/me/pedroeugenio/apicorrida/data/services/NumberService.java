package me.pedroeugenio.apicorrida.data.services;

import me.pedroeugenio.apicorrida.data.mappers.NumberMapper;
import me.pedroeugenio.apicorrida.data.repositories.NumberRepository;
import me.pedroeugenio.apicorrida.presenter.dto.NumberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<Integer, Integer> map = new HashMap<>();
        int[][] matriz = numberRepository.GetBiArray();

        for (int[] m : matriz) {
            for (int n : m) {
                for (int i : an) {
                    if (i == n) {
                        if (map.containsKey(i)) {
                            map.put(i, map.get(i) + 1);
                        } else {
                            map.put(i, 1);
                        }
                    } else if (!map.containsKey(i)) {
                        map.put(i, 0);
                    }
                }
            }
        }
        return numberMapper.mapNumbers(map);
    }

}
