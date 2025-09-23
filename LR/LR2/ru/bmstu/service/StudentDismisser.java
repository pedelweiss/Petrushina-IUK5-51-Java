package ru.bmstu.service;

import ru.bmstu.domain.Person;
import ru.bmstu.domain.Student;

public class StudentDismisser implements  Dismissable{
    @Override
    public void dismiss(Person p) {
        if (p instanceof Student) {
            Student s = (Student)p;
            System.out.println("Отчисление:" + p.getFirstName() + " " + p.getSecondName() + " из группы " + s.getGroup());
        } else {
            System.out.print("Не студент");
        }
    }
}
