package ch.nicksohl.winslow.domain;

import ch.nicksohl.winslow.domain.enumeration.Day;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "time_slots")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_slot_id", nullable = false)
    private Integer timeSlotId;

    @Enumerated(EnumType.STRING)
    @Column(name = "day", nullable = false, length = 16)
    private Day day;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;


    public Integer getId() {
        return timeSlotId;
    }

    public void setId(Integer id) {
        this.timeSlotId = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getDay() {
        return day.toString();
    }

    public void setDay(Day day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "day=" + day +
                '}';
    }
}