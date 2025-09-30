package ch.nicksohl.winslow.domain;

import ch.nicksohl.winslow.application.cqrs.dto.CourseDto;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private int courseId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "goals", nullable = false)
    private String goals;

    // ? Many entity instances (Courses) can be associated with one instance of another entity (Department)
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "department_id")
    private Department department;

    public void updateFromDto(CourseDto courseDto) {
        if (courseDto == null) {
            throw new IllegalArgumentException("CourseDto cannot be null");
        }
        setTitle(courseDto.title());
        setDescription(courseDto.description());
        setGoals(courseDto.goals());
        setDepartment(Department.fromDto(courseDto.department()));
    }


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

}