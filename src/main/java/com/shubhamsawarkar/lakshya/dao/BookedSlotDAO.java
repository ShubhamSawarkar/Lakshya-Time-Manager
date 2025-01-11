package com.shubhamsawarkar.lakshya.dao;

import com.shubhamsawarkar.lakshya.entity.BookedSlot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BookedSlotDAO extends CrudRepository<BookedSlot, Long> {

    @Query(value = "select max(endTime) from BookedSlot where endTime <= :before")
    LocalDateTime lastEndTimeBefore(LocalDateTime before);

    @Query(value = "select min(startTime) from BookedSlot where startTime >= :after")
    LocalDateTime nextStartTimeAfter(LocalDateTime after);

    @Query(value = "select s from BookedSlot s where slotId = (select min(slotId) from BookedSlot where startTime between :from and :to)")
    BookedSlot firstSlotInBetween(LocalDateTime from, LocalDateTime to);
}
