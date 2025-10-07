package ch.nicksohl.winslow.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    public Long getId() {
        return studentId;
    }

    public void setId(Long studentId) {
        this.studentId = studentId;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

}