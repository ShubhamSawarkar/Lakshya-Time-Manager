package com.shubhamsawarkar.lakshya.dto.slots;

import com.shubhamsawarkar.lakshya.dto.ResponseBody;
import com.shubhamsawarkar.lakshya.entity.BookedSlot;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public record SlotDetails(Long slotId
                        , String date
                        , String from
                        , String to
                        , String activity
                        , String progress) implements ResponseBody {

    public static SlotDetails of(BookedSlot slot) {
        return new SlotDetails(slot.getSlotId()
                 , slot.getDate().toString()
                 , slot.getStartTime().format(DateTimeFormatter.ISO_TIME)
                 , slot.getEndTime().format(DateTimeFormatter.ISO_TIME)
                 , slot.getActivity().getTitle()
                 , formatProgress(slot.getActivity().getProgress(), slot.getActivity().getLength()));
    }

    private static String formatProgress(short progress, short length) {
        return progress + "/" + length;
    }
}
