package com.bsuir.group;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name="students_groups")
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String specialty;

    public StudentGroup(){

    }

    public StudentGroup(Long id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentGroup)) return false;
        StudentGroup studentGroup = (StudentGroup) o;
        return Objects.equals(getId(), studentGroup.getId()) &&
                Objects.equals(getName(), studentGroup.getName()) &&
                Objects.equals(getSpecialty(), studentGroup.getSpecialty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSpecialty());
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
