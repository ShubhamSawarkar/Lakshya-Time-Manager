package com.shubhamsawarkar.lakshya.dto.slots;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record SlotBookRequest(
                              @NotNull(message = "A date must be specified in order to book a time slot")
                              @DateTimeFormat
                              LocalDate date
                            , LocalTime from
                            , LocalTime to
                            , Long activityId) {
}
