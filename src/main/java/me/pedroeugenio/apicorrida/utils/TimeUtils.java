package me.pedroeugenio.apicorrida.utils;

import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

@Component
public class TimeUtils {
    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("H:mm:ss.SSS")
            .toFormatter(Locale.ROOT);

    public static void main(String[] args) {
        System.out.println(new TimeUtils().parseTimeInStr("0:01:02.852"));
    }

    public LocalTime parseTimeInStr(String str) {
        if (str.split(":").length <= 2)
            str = "0:".concat(str);
        TemporalAccessor parsed = TIME_FORMATTER.parse(str);
        return LocalTime.from(parsed);
    }
}
