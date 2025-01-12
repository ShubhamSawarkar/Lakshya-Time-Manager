package com.shubhamsawarkar.lakshya.service.slot.impl;

import com.shubhamsawarkar.lakshya.constant.APIResponseStatus;
import com.shubhamsawarkar.lakshya.dao.ActivityDAO;
import com.shubhamsawarkar.lakshya.dao.BookedSlotDAO;
import com.shubhamsawarkar.lakshya.dto.APIResponse;
import com.shubhamsawarkar.lakshya.dto.slots.SlotBookRequest;
import com.shubhamsawarkar.lakshya.dto.slots.SlotDetails;
import com.shubhamsawarkar.lakshya.dto.slots.SlotFinalizeRequest;
import com.shubhamsawarkar.lakshya.entity.Activity;
import com.shubhamsawarkar.lakshya.entity.BookedSlot;
import com.shubhamsawarkar.lakshya.exception.InvalidRequestException;
import com.shubhamsawarkar.lakshya.service.slot.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Service("BookedSlotService")
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
    public APIResponse book(SlotBookRequest request) {
        Activity activity = activityDAO.findByActivityId(request.activityId());

        validateActivity(activity);
        validateSlot(request.date(), request.from(), request.to());

        BookedSlot slot = new BookedSlot();
        slot.setActivity(activity);
        slot.setDate(request.date());
        slot.setStartTime(request.from());
        slot.setEndTime(request.to());
        slot.setProgressFrom(activity.getProgress());
        slot.setProgressTo(activity.getProgress());
        slot.setFinalized(false);
        slot = slotDAO.save(slot);

        return new APIResponse(APIResponseStatus.OK
                 , "A slot is booked with the following details"
                 , SlotDetails.of(slot));
    }

    @Override
    public APIResponse finalize(SlotFinalizeRequest request) {
        return null;
    }

    private void validateActivity(Activity activity) {
        if (Objects.isNull(activity)) {
            throw new InvalidRequestException("Invalid Activity ID");
        }
        if (activity.getProgress() == activity.getLength()) {
            throw new InvalidRequestException("The activity \"" + activity.getTitle() + "\" is already completed. Please book the slot against a different activity or create a new activity");
        }
    }

    private void validateSlot(LocalDate date, LocalTime fromTime, LocalTime toTime) {
        BookedSlot overlappingSlot = fetchOverlappingSlot(date, fromTime, toTime);
        if (Objects.nonNull(overlappingSlot)) {
            throw new InvalidRequestException("The following existing slot overlaps with this time frame. Please reschedule the existing slot or try another time frame."
                                             , SlotDetails.of(overlappingSlot));
        }
    }

    private BookedSlot fetchOverlappingSlot(LocalDate date, LocalTime fromTime, LocalTime toTime) {
        LocalTime lastEndTime = slotDAO.lastEndTimeBefore(date, fromTime);
        LocalTime nextStartTime = slotDAO.nextStartTimeAfter(date, toTime);
        lastEndTime = Objects.nonNull(lastEndTime) ? lastEndTime : LocalTime.MIN;
        nextStartTime = Objects.nonNull(nextStartTime) ? nextStartTime.minusMinutes(1L) : LocalTime.MAX.truncatedTo(ChronoUnit.MINUTES);
        System.out.println(lastEndTime + "  -  " + nextStartTime);
        return slotDAO.firstSlotInBetween(date, lastEndTime, nextStartTime);
    }
}
