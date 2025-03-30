package com.shubhamsawarkar.lakshya.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shubhamsawarkar.lakshya.constant.DayType;

import java.time.LocalDate;
import java.util.List;

@JsonDeserialize(using = DaysDeserializer.class)
public record Days(DayType type
                 , LocalDate date
                 , List<Integer> days) {

    public Days(LocalDate date) {
        this(DayType.DATE, date, null);
    }

    public Days(DayType type, List<Integer> days) {
        this(type, null, days);
    }
}
