package com.shubhamsawarkar.lakshya.structures;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class Schedule implements AttributeConverter<Schedule, String> {

    private String cronExpr;

    public Schedule(String cronExpr) {
        this.cronExpr = cronExpr;
    }

    public String getCronExpr() {
        return cronExpr;
    }

    public void setCronExpr(String cronExpr) {
        this.cronExpr = cronExpr;
    }

    @Override
    public String convertToDatabaseColumn(Schedule schedule) {
        return cronExpr;
    }

    @Override
    public Schedule convertToEntityAttribute(String s) {
        return new Schedule(s);
    }
}
