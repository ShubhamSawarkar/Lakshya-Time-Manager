package com.shubhamsawarkar.lakshya.controller;

import com.shubhamsawarkar.lakshya.constant.DayType;
import com.shubhamsawarkar.lakshya.dto.APIResponse;
import com.shubhamsawarkar.lakshya.dto.slots.SlotBookRequest;
import com.shubhamsawarkar.lakshya.dto.slots.SlotFinalizeRequest;
import com.shubhamsawarkar.lakshya.service.slot.SlotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/slots")
@Validated
public class SlotController {

    private final SlotService bookedSlotService;

    private final SlotService recurrentSlotService;

    @Autowired
    public SlotController(@Qualifier("BookedSlotService") SlotService bookedSlotService
                        , @Qualifier("RecurrentSlotService") SlotService recurrentSlotService) {
        this.bookedSlotService = bookedSlotService;
        this.recurrentSlotService = recurrentSlotService;
    }

    @PostMapping
    public ResponseEntity<APIResponse> bookSlot(@Valid @RequestBody SlotBookRequest request) {
        APIResponse response = serviceFor(request).book(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{slotId}/finalize")
    public ResponseEntity<APIResponse> finalizeSlot(@PathVariable Long slotId, @Valid @RequestBody SlotFinalizeRequest request) {
        APIResponse response = bookedSlotService.finalize(slotId, request);
        return ResponseEntity.ok(response);
    }

    private SlotService serviceFor(SlotBookRequest request) {
        return DayType.DATE.equals(request.days().type()) ? bookedSlotService : recurrentSlotService;
    }
}
