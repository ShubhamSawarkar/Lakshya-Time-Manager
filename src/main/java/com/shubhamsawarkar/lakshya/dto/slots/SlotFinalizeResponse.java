package com.shubhamsawarkar.lakshya.dto.slots;

import java.time.LocalDateTime;

public record SlotFinalizeResponse(LocalDateTime from, LocalDateTime to, String activity, String progress) {
}
