package com.bsuir.teacher;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author ArtSCactus
 * @version 1.0
 */
@Entity
@Table(name = "teachers", schema = "university")
public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="name")
    @NotEmpty(message = "name was not provided")
    @Pattern(regexp = "[A-zА-я]+", message = "Invalid name format")
    private String name;
    @Column(name="surname")
    @NotEmpty(message = "surname was not provided")
    @Pattern(regexp = "[A-zА-я]+", message = "Invalid surname format")
    private String surname;

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
}
