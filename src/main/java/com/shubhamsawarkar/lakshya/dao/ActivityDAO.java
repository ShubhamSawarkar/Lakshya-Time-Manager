package com.shubhamsawarkar.lakshya.dao;

import com.shubhamsawarkar.lakshya.entity.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityDAO extends CrudRepository<Activity, Long> {

    Activity findByActivityId(Long activityId);
}
