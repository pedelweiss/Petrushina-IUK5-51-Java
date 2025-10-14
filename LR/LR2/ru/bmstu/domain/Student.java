package ru.bmstu.domain;

import java.util.Set;
import java.util.TreeSet;

public class Student extends Person {

    private String group;
    private int course;
    private final Set<Subject> subjects = new TreeSet<>();

    public Student(String firstName, String secondName, int age, String group, int course) {
        super(firstName, secondName, age);
        this.group = group;
        this.course = course;
    }

    public Student(String firstName, String secondName, int age, String phone, String group, int course) throws InvalidPhoneNumberException {
        super(firstName, secondName, age, phone);
        this.group = group;
        this.course = course;
    }

    public void addSubject(String name, int grade) {
        this.subjects.add(new Subject(name, grade));
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    // Cредний балл студента.
    public double getAverageGrade() {
        if (subjects.isEmpty()) {
            return 0.0;
        }
        double sum = subjects.stream()
                .mapToInt(Subject::getGrade)
                .sum();
        return sum / subjects.size();
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}