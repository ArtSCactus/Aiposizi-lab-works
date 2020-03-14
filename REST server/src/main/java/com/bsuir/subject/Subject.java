package com.bsuir.subject;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author ArtSCactus
 * @version 1.0
 */
@Entity
@Table(schema="university", name="subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer hours;

    public Subject() {
    }

    public Subject(Long id, String name, Integer hours) {
        this.id = id;
        this.name = name;
        this.hours = hours;
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

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return Objects.equals(getId(), subject.getId()) &&
                Objects.equals(getName(), subject.getName()) &&
                Objects.equals(getHours(), subject.getHours());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getHours());
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours='" + hours + '\'' +
                '}';
    }
}
