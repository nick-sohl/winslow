package ch.nicksohl.winslow.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SectionInstructorId implements Serializable {
    private static final long serialVersionUID = -5899239075703569000L;
    @Column(name = "instructor_id", nullable = false)
    private Integer instructorId;

    @Column(name = "section_id", nullable = false)
    private Integer sectionId;

    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SectionInstructorId entity = (SectionInstructorId) o;
        return Objects.equals(this.sectionId, entity.sectionId) &&
                Objects.equals(this.instructorId, entity.instructorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectionId, instructorId);
    }

}