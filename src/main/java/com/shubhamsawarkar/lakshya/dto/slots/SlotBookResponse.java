package com.shubhamsawarkar.lakshya.dto.slots;

import java.time.LocalDateTime;

public record SlotBookResponse(Long slotId, LocalDateTime from, LocalDateTime to, String activity, String progress) {
}
