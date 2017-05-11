package by.triumgroup.recourse.entity.dto;

import by.triumgroup.recourse.entity.model.User;

public class StudentWithMark extends User {
    private Double mark;

    public StudentWithMark(String name, String surname, Double mark) {
        this.mark = mark;
        setName(name);
        setSurname(surname);
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }
}
