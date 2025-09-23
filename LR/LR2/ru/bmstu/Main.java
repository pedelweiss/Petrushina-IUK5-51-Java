package ru.bmstu;

import ru.bmstu.domain.Person;
import ru.bmstu.service.PersonDemo;

public class Main {
    public static void main(String[] args) {

        Person teacher = new Person("Сергей", "Петров", 45);
        PersonDemo teacherDemo = new PersonDemo(teacher);
        teacherDemo.demo();

        PersonDemo.demo1();
    }
}