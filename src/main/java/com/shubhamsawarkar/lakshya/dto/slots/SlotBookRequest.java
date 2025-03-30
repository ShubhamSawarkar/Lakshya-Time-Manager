package com.shubhamsawarkar.lakshya.dto.slots;

import com.shubhamsawarkar.lakshya.dto.Days;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record SlotBookRequest(Days days
                            , @NotNull(message = "A 'from' time must be specified in order to book a time slot")
                              LocalTime from
                            , @NotNull(message = "A 'to' time must be specified in order to book a time slot")
                              LocalTime to
                            , @NotNull(message = "An activityId must be specified in order to book a time slot")
                              @Digits(integer = 6, fraction = 0, message = "Activity ID cannot be greater than 10^6")
                              Long activityId) {
}
