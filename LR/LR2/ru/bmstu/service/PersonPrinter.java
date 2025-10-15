package ru.bmstu.service;

import ru.bmstu.domain.Person;
import ru.bmstu.domain.Student;
import ru.bmstu.domain.Subject;

public final class PersonPrinter {

    private PersonPrinter() {
    }

    public static void printFI(Person p) {
        System.out.println("Имя и фамилия: " + p.getFirstName() + " " + p.getSecondName());
    }

    public static void printInfo(Person p) {
        System.out.println("--- Полная информация ---");
        System.out.println("Имя: " + p.getFirstName());
        System.out.println("Фамилия: " + p.getSecondName());
        System.out.println("Возраст: " + p.getAge());
        String phoneInfo = (p.getPhone() != null) ? p.getPhone() : "не указан";
        System.out.println("Телефон: " + phoneInfo);

        if (p instanceof Student) {
            Student student = (Student) p;
            System.out.println("Группа: " + student.getGroup());
            System.out.println("Курс: " + student.getCourse());

            if (student.getSubjects().isEmpty()) {
                System.out.println("Дисциплины: не указаны");
            } else {
                System.out.println("Дисциплины (ср. балл: " + String.format("%.2f", student.getAverageGrade()) + "):");
                for (Subject subject : student.getSubjects()) {
                    System.out.println("- " + subject);
                }
            }
        }
        System.out.println("------------------------------------");
    }
}