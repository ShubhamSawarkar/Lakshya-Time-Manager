package com.shubhamsawarkar.lakshya.dto.slots;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public record SlotFinalizeRequest(
                                  @NotNull(message = "A progress value must be specified in order to finalize a time slot")
                                  @Digits(integer = 4, fraction = 0, message = "A progress values must be less than 10^4")
                                  Short progress) {
}
