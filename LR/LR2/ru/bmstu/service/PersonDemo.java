package ru.bmstu.service;

import ru.bmstu.domain.InvalidPhoneNumberException;
import ru.bmstu.domain.Person;
import ru.bmstu.domain.Student;

import java.util.List;

public class PersonDemo {

    public static void demonstrateStudentManager() {
        System.out.println("\n--- 🚀 Демонстрация работы StudentManager ---");
        StudentManager manager = new StudentManager();

        // --- Студент 1
        try {
            Student student1 = new Student("Иван", "Сидоров", 20, "+79991112233", "ИУ5-31Б", 2);
            student1.addSubject("Программирование", 5);
            student1.addSubject("Математический анализ", 4);
            student1.addSubject("Философия", 5); // ср. балл = 4.67
            manager.addStudent(student1);
        } catch (InvalidPhoneNumberException e) {
            System.out.println("Ошибка при добавлении: " + e.getMessage());
        }

        // --- Студент 2 (с неверным номером) ---
        try {

            Student student2 = new Student("Мария", "Петрова", 19, "неверный-номер", "ИУ5-32Б", 2);
            student2.addSubject("Базы данных", 5);
            student2.addSubject("Физика", 3);
            manager.addStudent(student2);
        } catch (InvalidPhoneNumberException e) {
            // Программа поймает исключение здесь, выведет сообщение и продолжит работу
            System.out.println("Ошибка при добавлении: " + e.getMessage());
        }

        // --- Студент 3  ---
        try {
            Student student3 = new Student("Алексей", "Смирнов", 21, "89169876543", "РК6-41М", 3);
            student3.addSubject("Теория вероятностей", 5);
            student3.addSubject("Операционные системы", 5); // ср. балл = 5.0
            manager.addStudent(student3);
        } catch (InvalidPhoneNumberException e) {
            System.out.println("Ошибка при добавлении: " + e.getMessage());
        }

        System.out.println("\n--- Все студенты в базе ---");
        for(Student s : manager.getAllStudents()) {
            PersonPrinter.printInfo(s);
        }

        // --- 2. Демонстрация поиска ---
        System.out.println("\n--- Этап 2: Поиск студента 'Мария Петрова' ---");
        Student foundStudent = manager.findByName("Мария Петрова");
        if (foundStudent != null) {
            PersonPrinter.printInfo(foundStudent);
        } else {
            System.out.println("Студент не найден.");
        }

        // --- 3. Демонстрация фильтрации по среднему баллу ---
        System.out.println("\n--- Этап 3: Студенты со средним баллом >= 4.5 ---");
        List<Student> highAchievers = manager.findByAverageGrade(4.5);
        if (highAchievers.isEmpty()){
            System.out.println("Таких студентов нет.");
        } else {
            highAchievers.forEach(PersonPrinter::printInfo);
        }

        // --- 4. Демонстрация удаления ---
        System.out.println("\n--- Этап 4: Удаление студента 'Иван Сидоров' ---");
        manager.removeStudent("Иван Сидоров");

        System.out.println("\n--- Список студентов после удаления ---");
        manager.getAllStudents().forEach(PersonPrinter::printInfo);

        System.out.println("\n--- Демонстрация StudentManager завершена ---");
    }
}