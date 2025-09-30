package ch.nicksohl.winslow.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "section_instructor")
public class SectionInstructor {
    @EmbeddedId
    private SectionInstructorId id;

    @MapsId("instructorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @MapsId("sectionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    public SectionInstructorId getId() {
        return id;
    }

    public void setId(SectionInstructorId id) {
        this.id = id;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

}