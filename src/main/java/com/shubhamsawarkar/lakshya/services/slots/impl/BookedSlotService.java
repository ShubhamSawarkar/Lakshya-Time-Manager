package com.shubhamsawarkar.lakshya.services.slots.impl;

import com.shubhamsawarkar.lakshya.constants.APIResponseStatus;
import com.shubhamsawarkar.lakshya.dao.ActivityDAO;
import com.shubhamsawarkar.lakshya.dao.BookedSlotDAO;
import com.shubhamsawarkar.lakshya.dto.APIResponse;
import com.shubhamsawarkar.lakshya.dto.slots.SlotBookRequest;
import com.shubhamsawarkar.lakshya.dto.slots.SlotDetails;
import com.shubhamsawarkar.lakshya.dto.slots.SlotFinalizeRequest;
import com.shubhamsawarkar.lakshya.entities.Activity;
import com.shubhamsawarkar.lakshya.entities.BookedSlot;
import com.shubhamsawarkar.lakshya.services.slots.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class BookedSlotService implements SlotService {

    private final BookedSlotDAO slotDAO;

    private final ActivityDAO activityDAO;

    @Autowired
    public BookedSlotService(BookedSlotDAO slotDAO, ActivityDAO activityDAO) {
        this.slotDAO = slotDAO;
        this.activityDAO = activityDAO;
    }

    @Override
    @Transactional
    public APIResponse<SlotDetails> book(SlotBookRequest request) {
        Activity activity = activityDAO.findByActivityId(request.activityId());
        if (Objects.isNull(activity)) {
            return new APIResponse<>(APIResponseStatus.INVALID_REQUEST
                     , "Invalid Activity ID");
        }
        if (activity.getProgress() == activity.getLength()) {
            return new APIResponse<>(APIResponseStatus.INVALID_REQUEST
                    , "The activity \"" + activity.getTitle() + "\" is already completed. Please book the slot against a different activity or create a new activity");
        }

        BookedSlot overlappingSlot = fetchOverlappingSlot(request.from(), request.to());
        if (Objects.nonNull(overlappingSlot)) {
            return new APIResponse<>(APIResponseStatus.INVALID_REQUEST
                     , "The following existing slot overlaps with this time frame. Please reschedule the existing slot or try another time frame."
                     , SlotDetails.of(overlappingSlot));
        }

        BookedSlot slot = new BookedSlot();
        slot.setActivity(activity);
        slot.setStartTime(request.from());
        slot.setEndTime(request.to());
        slot.setProgressFrom(activity.getProgress());
        slot.setProgressTo(activity.getProgress());
        slot.setFinalized(false);
        slot = slotDAO.save(slot);

        return new APIResponse<>(APIResponseStatus.OK
                 , "A slot is booked with the following details"
                 , SlotDetails.of(slot));
    }

    @Override
    public APIResponse<SlotDetails> finalize(SlotFinalizeRequest request) {
        return null;
    }

    private BookedSlot fetchOverlappingSlot(LocalDateTime from, LocalDateTime to) {
        LocalDateTime lastEndTime = slotDAO.lastEndTimeBefore(from);
        LocalDateTime nextStartTime = slotDAO.nextStartTimeAfter(to);
        return slotDAO.firstSlotInBetween(lastEndTime, nextStartTime);
    }
}
