package ru.bmstu.service;

import ru.bmstu.domain.Person;
import ru.bmstu.domain.Student;

public class PersonDemo {

    private final Person person;

    public PersonDemo(Person person) {
        this.person = person;
    }

    public void demo() {
        System.out.println("Запуск демонстрации для объекта Person...");
        PersonPrinter.printFI(this.person);
        PersonPrinter.printInfo(this.person);
        System.out.println("Демонстрация завершена.");
    }

    public static void demo1() {
        System.out.println("Запуск демонстрации для объекта Student...");
        Student student = new Student("Иван", "Сидоров", 20, "555-0202", "ИУК5-31Б", 2);

        System.out.println("\n--- Демонстрация интерфейса Dismissable ---");
        Dismissable universityAdmin = new StudentDismisser();
        universityAdmin.dismiss(student);

        System.out.println("Демонстрация завершена.");
    }
}
