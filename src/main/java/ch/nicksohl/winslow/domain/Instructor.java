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
    private Integer instructorId;

    @Column(name = "firstname", nullable = false, length = 200)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 200)
    private String lastname;

    @Column(name = "email", nullable = false, length = 200)
    private String email;

    @Embedded
    private Password password;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "department_id")
    private Department department;

    public Integer getId() {
        return instructorId;
    }

    public void setId(Integer id) {
        this.instructorId = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail(String email) {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}