package ch.nicksohl.winslow.domain;

import ch.nicksohl.winslow.application.cqrs.dto.DepartmentDto;
import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    // ? To create a new course we need to provide a department object
    // ? Because we retrieve a DTO from the Department Service, we must map the DTO back
    public static Department fromDto(DepartmentDto departmentDto) {
        if (departmentDto == null) {
            return null;
        }
        Department department = new Department();
        department.setId(departmentDto.id());
        department.setName(departmentDto.name());
        return department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}