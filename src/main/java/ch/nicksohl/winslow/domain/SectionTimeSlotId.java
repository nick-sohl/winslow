package ch.nicksohl.winslow.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SectionTimeSlotId implements Serializable {
    private static final long serialVersionUID = -7331653417674395476L;
    @Column(name = "section_id", nullable = false)
    private Integer sectionId;

    @Column(name = "time_slot_id", nullable = false)
    private Integer timeSlotId;

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(Integer timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SectionTimeSlotId entity = (SectionTimeSlotId) o;
        return Objects.equals(this.timeSlotId, entity.timeSlotId) &&
                Objects.equals(this.sectionId, entity.sectionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeSlotId, sectionId);
    }

}