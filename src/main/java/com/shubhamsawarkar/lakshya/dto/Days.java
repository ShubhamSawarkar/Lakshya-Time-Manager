package com.shubhamsawarkar.lakshya.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shubhamsawarkar.lakshya.constant.DayType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@JsonDeserialize(using = DaysDeserializer.class)
public record Days(List<Day> days) {
    record Day(
            LocalDate date,
            DayOfWeek dayOfWeek,
            Byte dayOfMonth,
            DayType type) {

        public Day(LocalDate date) {
            this(date, null, null, DayType.EXACT_DATE);
        }

        public Day(DayOfWeek dayOfWeek) {
            this(null, dayOfWeek, null, DayType.DAY_OF_THE_WEEK);
        }

        public Day(Byte dayOfMonth) {
            this(null, null, dayOfMonth, DayType.DAY_OF_THE_MONTH);
        }
    }
}
