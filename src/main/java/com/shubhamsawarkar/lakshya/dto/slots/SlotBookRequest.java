package com.shubhamsawarkar.lakshya.dto.slots;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SlotBookRequest(@NotNull(message = "'from' is mandatory")
                              @Future(message = "'from' must be a time in future")
                              LocalDateTime from
                            , @NotNull(message = "'to' is mandatory")
                              LocalDateTime to
                            , @NotNull(message = "'activityId' is mandatory")
                              Long activityId) {
}
