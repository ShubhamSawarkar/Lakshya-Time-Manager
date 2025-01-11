package com.shubhamsawarkar.lakshya.dto.slots;

import com.shubhamsawarkar.lakshya.dto.ResponseBody;
import com.shubhamsawarkar.lakshya.entity.BookedSlot;

import java.time.LocalDateTime;

public record SlotDetails(Long slotId
                        , LocalDateTime from
                        , LocalDateTime to
                        , String activity
                        , String progress) implements ResponseBody {

    public static SlotDetails of(BookedSlot slot) {
        return new SlotDetails(slot.getSlotId()
                 , slot.getStartTime()
                 , slot.getEndTime()
                 , slot.getActivity().getTitle()
                 , formatProgress(slot.getActivity().getProgress(), slot.getActivity().getLength()));
    }

    private static String formatProgress(short progress, short length) {
        return progress + "/" + length;
    }
}
