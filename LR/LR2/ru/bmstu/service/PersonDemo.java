package ru.bmstu.service;

import ru.bmstu.domain.Person;

public class PersonDemo {

    private final Person person;

    public PersonDemo(Person person) {
        this.person = person;
    }

    public void demo() {
        System.out.println("====== НАЧАЛО ДЕМОНСТРАЦИИ ======");
        PersonPrinter.printFI(this.person);
        PersonPrinter.printInfo(this.person);

        System.out.println("\n--- Демонстрация интерфейса Dismissable ---");
        Dismissable universityAdmin = new Dismissable() {};

        universityAdmin.dismiss(this.person); // Отчисляем "главного"

        System.out.println("\n====== КОНЕЦ ДЕМОНСТРАЦИИ ======");
    }
}
