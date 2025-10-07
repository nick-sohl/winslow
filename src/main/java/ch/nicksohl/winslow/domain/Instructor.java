package ch.nicksohl.winslow.domain;

import ch.nicksohl.winslow.domain.value_object.Password;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instructor_id", nullable = false)
    private Long instructorId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    public Long getId() {
        return instructorId;
    }

    public void setId(Long id) {
        this.instructorId = id;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

}