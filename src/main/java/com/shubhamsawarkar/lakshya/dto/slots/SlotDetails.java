package com.shubhamsawarkar.lakshya.dto.slots;

import com.shubhamsawarkar.lakshya.entities.BookedSlot;

import java.time.LocalDateTime;

public record SlotDetails(Long slotId, LocalDateTime from, LocalDateTime to, String activity, String progress) {

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
