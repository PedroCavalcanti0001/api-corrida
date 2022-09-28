package me.pedroeugenio.apicorrida.data.mappers;

import me.pedroeugenio.apicorrida.presenter.dto.NumberDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class NumberMapper {

    public List<NumberDto> mapNumbers(Map<Integer, Integer> numbers) {
        return numbers.entrySet().stream().map(e -> new NumberDto(e.getKey(), e.getValue())).collect(Collectors.toList());
    }
}
