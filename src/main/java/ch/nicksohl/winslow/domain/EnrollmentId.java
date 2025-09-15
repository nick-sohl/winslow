package ch.nicksohl.winslow.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnrollmentId implements Serializable {
    @Serial
    private static final long serialVersionUID = -4465722108804071604L;
    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @Column(name = "section_id", nullable = false)
    private Integer sectionId;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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
        EnrollmentId entity = (EnrollmentId) o;
        return Objects.equals(this.studentId, entity.studentId) &&
                Objects.equals(this.sectionId, entity.sectionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, sectionId);
    }

}