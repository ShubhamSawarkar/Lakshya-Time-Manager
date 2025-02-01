package com.shubhamsawarkar.lakshya.service.slot;

import com.shubhamsawarkar.lakshya.dto.APIResponse;
import com.shubhamsawarkar.lakshya.dto.slots.SlotBookRequest;
import com.shubhamsawarkar.lakshya.dto.slots.SlotFinalizeRequest;

public interface SlotService {

    APIResponse book(SlotBookRequest request);

    APIResponse finalize(Long slotId, SlotFinalizeRequest request);
}
