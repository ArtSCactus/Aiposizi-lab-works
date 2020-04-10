package com.bsuir.lesson;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.Objects;

@Entity
@Table(name = "lessons", schema = "university")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "lessons.group")
    @DecimalMin(value = "0", message = "Invalid group id. 0 is minimal")
    private Long groupId;
    @Column(name = "subject")
    @DecimalMin(value = "0", message = "Invalid subject id. 0 is minimal")
    private Long subjectId;
    @Column(name = "teacher")
    @DecimalMin(value = "0", message = "Invalid teacher id. 0 is minimal")
    private Long teacherId;

    public Lesson() {
    }

    public Lesson(Long id, Long groupId, Long subjectId, Long teacherId) {
        this.id = id;
        this.groupId = groupId;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lesson)) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(getId(), lesson.getId()) &&
                Objects.equals(getGroupId(), lesson.getGroupId()) &&
                Objects.equals(getSubjectId(), lesson.getSubjectId()) &&
                Objects.equals(getTeacherId(), lesson.getTeacherId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGroupId(), getSubjectId(), getTeacherId());
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", subjectId=" + subjectId +
                ", teacherId=" + teacherId +
                '}';
    }
}
