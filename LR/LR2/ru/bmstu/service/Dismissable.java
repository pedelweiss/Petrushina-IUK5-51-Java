package ru.bmstu.service;

import ru.bmstu.domain.Person;
import ru.bmstu.domain.Student;

public interface Dismissable {


    default void dismiss(Person p) {
        System.out.print("Отчисление: " + p.getFirstName() + " " + p.getSecondName());

        if (p instanceof Student) {
            Student s = (Student)p;
            System.out.println(" из группы " + s.getGroup());
        } else {
            System.out.println();
        }
    }
}