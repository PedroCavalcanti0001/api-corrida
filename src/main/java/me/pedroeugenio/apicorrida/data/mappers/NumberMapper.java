package me.pedroeugenio.apicorrida.data.mappers;

import me.pedroeugenio.apicorrida.presenter.dto.NumberDto;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NumberMapper {

    public List<NumberDto> mapNumbers(List<ImmutablePair<Integer, Integer>> numbers) {
        return numbers.stream().map(e -> new NumberDto(e.getKey(), e.getValue())).collect(Collectors.toList());
    }
}
