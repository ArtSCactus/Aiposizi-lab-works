package com.bsuir.student;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import java.util.Objects;
@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Pattern(regexp = "[A-zА-я]+", message = "Invalid name format")
    private String name;
    @Pattern(regexp = "[A-zА-я]+", message = "Invalid surname format")
    private String surname;
    @DecimalMin(value = "0", message = "Minimal value is 0")
    private Long rating;
    @Column(name="students.group")
    @DecimalMin(value = "0", message = "Invalid group number. 0 is minimal")
    private Long group;

    public Student(){

    }

    public Student(Long id, String name, String surname, Long rating, Long group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.rating = rating;
        this.group = group;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getId(), student.getId()) &&
                Objects.equals(getName(), student.getName()) &&
                Objects.equals(getSurname(), student.getSurname()) &&
                Objects.equals(getRating(), student.getRating()) &&
                Objects.equals(getGroup(), student.getGroup());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getRating(), getGroup());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", rating=" + rating +
                ", group='" + group + '\'' +
                '}';
    }
}
