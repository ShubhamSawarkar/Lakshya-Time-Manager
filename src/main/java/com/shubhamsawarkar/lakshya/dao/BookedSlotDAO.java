package com.shubhamsawarkar.lakshya.dao;

import com.shubhamsawarkar.lakshya.entity.BookedSlot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface BookedSlotDAO extends CrudRepository<BookedSlot, Long> {

    @Query(value = "select max(endTime) from BookedSlot where date = :date and endTime <= :before")
    LocalTime lastEndTimeBefore(LocalDate date, LocalTime before);

    @Query(value = "select min(startTime) from BookedSlot where date = :date and startTime >= :after")
    LocalTime nextStartTimeAfter(LocalDate date, LocalTime after);

    @Query(value = "select s from BookedSlot s where date = :date and startTime = (select min(startTime) from BookedSlot where date = :date and startTime between :from and :to)")
    BookedSlot firstSlotInBetween(LocalDate date, LocalTime from, LocalTime to);
}
