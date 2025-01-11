package com.shubhamsawarkar.lakshya.entity;

import com.shubhamsawarkar.lakshya.constant.ActivityCategory;
import jakarta.persistence.*;

@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @Column(name = "activity_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @Column(name = "category")
    @Enumerated(value = EnumType.STRING)
    private ActivityCategory category;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "progress")
    private short progress;

    @Column(name = "length")
    private short length;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public ActivityCategory getCategory() {
        return category;
    }

    public void setCategory(ActivityCategory category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getProgress() {
        return progress;
    }

    public void setProgress(short progress) {
        this.progress = progress;
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }
}
