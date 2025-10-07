package ch.nicksohl.winslow.domain;

import ch.nicksohl.winslow.application.shared.Result;
import ch.nicksohl.winslow.domain.enumeration.Role;
import ch.nicksohl.winslow.domain.value_object.Email;
import ch.nicksohl.winslow.domain.value_object.Password;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "firstname", nullable = false, length = 200)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 200)
    private String lastname;

    @Column(name = "username", nullable = false, length = 200, unique = true)
    private String username;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "department_id")
    private Department department;


    public Long getId() { return userId; }

    public void setId(Long userId) { this.userId = userId; }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email.getEmail();
    }

    public void setEmail(String email) {
        Result<Email> result = Email.of(email);
        this.email = result.data();
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public String getRole() {
        return role.toString();
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                ", role=" + role +
                ", department=" + department +
                '}';
    }
}
