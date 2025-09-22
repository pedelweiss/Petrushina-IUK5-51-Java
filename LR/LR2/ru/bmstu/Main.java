package ru.bmstu;

import ru.bmstu.domain.Person;
import ru.bmstu.domain.Student;
import ru.bmstu.service.PersonDemo;

public class Main {
    public static void main(String[] args) {

        Person teacher = new Person("Сергей", "Петров", 45);
        Student student = new Student("Иван", "Сидоров", 20, "555-0202", "ИУК5-31Б", 2);

        System.out.println("### Демонстрация для объекта Person (Учитель) ###");
        PersonDemo teacherDemo = new PersonDemo(teacher);
        teacherDemo.demo();

        System.out.println("\n### Демонстрация для объекта Student (Студент) ###");
        PersonDemo studentDemo = new PersonDemo(student);
        studentDemo.demo();
    }
}