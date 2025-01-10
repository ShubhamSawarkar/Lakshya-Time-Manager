package com.shubhamsawarkar.lakshya.services.slots;

import com.shubhamsawarkar.lakshya.dto.APIResponse;
import com.shubhamsawarkar.lakshya.dto.slots.SlotBookRequest;
import com.shubhamsawarkar.lakshya.dto.slots.SlotDetails;
import com.shubhamsawarkar.lakshya.dto.slots.SlotFinalizeRequest;

public interface SlotService {

    APIResponse<SlotDetails> book(SlotBookRequest request);

    APIResponse<SlotDetails> finalize(SlotFinalizeRequest request);
}
