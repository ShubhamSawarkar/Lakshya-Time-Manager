package com.shubhamsawarkar.lakshya.dto.slots;

import java.time.LocalDate;
import java.time.LocalTime;

public record SlotBookRequest(LocalDate date
                            , LocalTime from
                            , LocalTime to
                            , Long activityId) {
}
