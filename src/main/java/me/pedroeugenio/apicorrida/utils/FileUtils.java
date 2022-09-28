package me.pedroeugenio.apicorrida.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import me.pedroeugenio.apicorrida.data.services.RunningService;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FileUtils {

    public BufferedReader loadFile(String name) {
        InputStream resourceAsStream = RunningService.class.getClassLoader().getResourceAsStream("data.csv");
        return new BufferedReader(new InputStreamReader(Objects.requireNonNull(resourceAsStream),
                StandardCharsets.UTF_8));
    }

    public List<String[]> readCsvLines(String name) {
        BufferedReader reader = loadFile(name);
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
        List<String[]> lines = new ArrayList<>();
        CSVReader reader2 = new CSVReaderBuilder(
                reader)
                .withCSVParser(csvParser)
                .withSkipLines(1)
                .build();
        String[] lineInArray = null;
        while (true) {
            try {
                if ((lineInArray = reader2.readNext()) == null) break;
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
            lines.add(lineInArray);
        }
        return lines;
    }
}
