package com.shubhamsawarkar.lakshya.controller;

import com.shubhamsawarkar.lakshya.dto.APIResponse;
import com.shubhamsawarkar.lakshya.dto.slots.SlotBookRequest;
import com.shubhamsawarkar.lakshya.service.slot.SlotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/slots")
@Validated
public class SlotController {

    private final SlotService bookedSlotService;

    @Autowired
    public SlotController(@Qualifier("BookedSlotService") SlotService bookedSlotService) {
        this.bookedSlotService = bookedSlotService;
    }

    @PostMapping
    public ResponseEntity<APIResponse> bookSlot(@Valid @RequestBody SlotBookRequest request) {
        APIResponse response = bookedSlotService.book(request);
        return ResponseEntity.ok(response);
    }
}
