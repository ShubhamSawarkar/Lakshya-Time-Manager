package com.shubhamsawarkar.lakshya.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.shubhamsawarkar.lakshya.constant.DayType;
import com.shubhamsawarkar.lakshya.exception.InvalidRequestException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DaysDeserializer extends StdDeserializer<Days> {

    private static final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";
    private static final String WEEK_DAY_PATTERN = "^(?:MON|TUE|WED|THU|FRI|SAT|SUN)(?:,(?:MON|TUE|WED|THU|FRI|SAT|SUN))*$";
    private static final String MONTH_DAY_PATTERN = "^(?:[1-9]|[12]\\d|3[0-1])(?:,([1-9]|[12]\\d|3[0-1]))*$";
    private static final Map<String, DayOfWeek> WEEK_DAY_INDICES = Arrays.stream(DayOfWeek.values())
                                                                  .collect(Collectors.toMap(
                                                                          weekDay -> weekDay.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                                                                        , weekDay -> weekDay));

    public DaysDeserializer() {
        this(null);
    }

    public DaysDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Days deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String daySpec = node.get("day").asText();

        DayType dayType = typeOf(daySpec);
        if (Objects.isNull(dayType)) {
            throw new InvalidRequestException("Invalid 'day' specification - A day can be an exact date in format 'yyyy-MM-dd'" +
                    " OR a comma-separated list of week days (MON to SUN) / month days (1-31)");
        }

        return switch (dayType) {
            case EXACT_DATE -> exactDateDaysOf(daySpec);
            case DAY_OF_THE_MONTH -> monthDaysOf(daySpec);
            case DAY_OF_THE_WEEK -> weekDaysOf(daySpec);
        };
    }

    private DayType typeOf(String daySpec) {
        Pattern datePattern = Pattern.compile(DATE_PATTERN);
        Pattern weekDayPattern = Pattern.compile(WEEK_DAY_PATTERN);
        Pattern monthDayPattern = Pattern.compile(MONTH_DAY_PATTERN);

        DayType dayType = null;
        if (StringUtils.hasText(daySpec)) {
            if (datePattern.matcher(daySpec).matches()) {
                dayType = DayType.EXACT_DATE;
            } else if (weekDayPattern.matcher(daySpec).matches()) {
                dayType = DayType.DAY_OF_THE_WEEK;
            } else if (monthDayPattern.matcher(daySpec).matches()) {
                dayType = DayType.DAY_OF_THE_MONTH;
            }
        }
        return dayType;
    }

    private Days exactDateDaysOf(String daySpec) {
        return new Days(Collections.singletonList(new Days.Day(LocalDate.parse(daySpec))));
    }

    private Days monthDaysOf(String daySpec) {
        List<Days.Day> monthDays = Arrays.stream(daySpec.split(","))
                                  .map(Byte::parseByte)
                                  .map(Days.Day::new)
                                  .toList();
        return new Days(monthDays);
    }

    private Days weekDaysOf(String daySpec) {
        List<Days.Day> weekDays = Arrays.stream(daySpec.split(","))
                                 .map(WEEK_DAY_INDICES::get)
                                 .map(Days.Day::new)
                                 .toList();
        return new Days(weekDays);
    }
}
