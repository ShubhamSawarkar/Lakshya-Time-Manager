package com.shubhamsawarkar.lakshya.entities;

import com.shubhamsawarkar.lakshya.structures.Schedule;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "recurrent_slot")
public class RecurrentSlot {

    @Id
    @Column(name = "slot_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slotId;

    @Column(name = "schedule")
    @Convert(converter = Schedule.class)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
