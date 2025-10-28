// Файл: Demo.java
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {

        System.out.println("--- 1. Демонстрация Задачи 1 (Студенты) ---");
        List<Student> students = List.of(
                new Student("Анна", 21, Map.of("Математика", 90, "Физика", 85)),
                new Student("Борис", 19, Map.of("История", 85)),
                new Student("Виктор", 22, Map.of("Химия", 75)),
                new Student("Дарья", 21, Map.of("Литература", 70, "Биология", 95))
        );
        List<String> studentNames = Tasks.task1(students);
        System.out.println("Студенты, прошедшие фильтр: " + studentNames);
        System.out.println("-------------------------------------------\n");


        System.out.println("--- 2. Демонстрация Задачи 2 (Сотрудники) ---");
        List<Employee> employees = List.of(
                new Employee("Егор", 25, "IT", 60000),
                new Employee("Федор", 30, "HR", 55000),
                new Employee("Галина", 22, "IT", 70000),
                new Employee("Иван", 28, "IT", 45000),
                new Employee("Ирина", 24, "IT", 80000)
        );
        System.out.println("Топ-3 самых молодых сотрудника в IT с ЗП > 50000:");
        Tasks.task2(employees);
        System.out.println("--------------------------------------------\n");


        System.out.println("--- 3. Демонстрация Задачи 3 (Заказы) ---");
        Item item1 = new Item("clothing", 50);
        Item item2 = new Item("book", 20);
        Item item3 = new Item("clothing", 30);
        Item item4 = new Item("electronics", 200);

        List<Order> orders = List.of(
                new Order(1, List.of(item1, item2), LocalDate.now().minusDays(10), "ClientA"),
                new Order(2, List.of(item3), LocalDate.now().minusMonths(2), "ClientB"), // Старый
                new Order(3, List.of(item3, item4), LocalDate.now().minusDays(5), "ClientA"),
                new Order(4, List.of(item2, item4), LocalDate.now().minusDays(2), "ClientC") // Нет 'clothing'
        );
        Tasks.task3(orders);
        System.out.println("-----------------------------------------\n");


        System.out.println("--- 4. Демонстрация Задачи 4 (Товары) ---");
        List<Product> products = List.of(
                new Product("Ноутбук", 1500, 10),
                new Product("Мышь", 40, 20), // Цена < 50
                new Product("Клавиатура", 60, 0), // Кол-во = 0
                new Product("Монитор", 300, 5)
        );
        // Ожидаемый результат:
        // Фильтр (Цена>50, Кол-во>0): Ноутбук(10), Монитор(5)
        // Сортировка (по кол-ву): Монитор(5), Ноутбук(10)
        // Имена: [Монитор, Ноутбук]
        List<String> productNames = Tasks.task4(products);
        System.out.println("Товары, прошедшие фильтр: " + productNames);
        System.out.println("-----------------------------------------\n");


        System.out.println("--- 5. Демонстрация Задачи 5 (Клиенты) ---");
        // Покупки для Клиента 1
        Purchase p1 = new Purchase("electronics", 500, LocalDate.now().minusDays(7)); // < 2 нед.
        Purchase p2 = new Purchase("food", 50, LocalDate.now().minusDays(20)); // > 2 нед.
        Purchase p3 = new Purchase("electronics", 600, LocalDate.now().minusDays(25)); // > 2 нед.
        // Покупки для Клиента 2
        Purchase p4 = new Purchase("books", 100, LocalDate.now().minusDays(10));
        // Покупки для Клиента 3
        Purchase p5 = new Purchase("electronics", 1200, LocalDate.now().minusDays(3)); // < 2 нед.

        Customer c1 = new Customer("c1@mail.com", List.of(p1, p2, p3)); // Сумма за мес: 500+50+600 = 1150 (Проходит)
        Customer c2 = new Customer("c2@mail.com", List.of(p4));         // Сумма за мес: 100 (Не проходит)
        Customer c3 = new Customer("c3@mail.com", List.of(p5, p4)); // Сумма за мес: 1200+100 = 1300 (Проходит)

        List<Customer> customers = List.of(c1, c2, c3);

        System.out.println("Email клиентов до: [c1@mail.com, c2@mail.com, c3@mail.com]");
        System.out.println("Сумма p1 до: " + p1.getAmount());
        System.out.println("Сумма p5 до: " + p5.getAmount());

        List<String> customerEmails = Tasks.task5(customers);

        System.out.println("\nОтсортированный список Email: " + customerEmails);
        System.out.println("Сумма p1 после: " + p1.getAmount()); // Проверка side-effect
        System.out.println("Сумма p5 после: " + p5.getAmount()); // Проверка side-effect
        System.out.println("-----------------------------------------\n");
    }
}