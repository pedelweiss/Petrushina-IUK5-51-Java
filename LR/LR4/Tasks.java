// Файл: Lab4.java
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tasks {

    // Задача 1: Фильтрация студентов
    public static List<String> task1(List<Student> students) {
        return students.stream() // 
                .filter(s -> s.getAge() > 20) // 
                .filter(s -> s.getGrades().values().stream().anyMatch(grade -> grade > 80)) // 
                .sorted(Comparator.comparing(Student::getName)) // 
                .map(Student::getName) // 
                .collect(Collectors.toList()); // 
    }

    // Задача 2: Фильтрация сотрудников
    public static void task2(List<Employee> employees) {
        employees.stream() // 
                .filter(e -> e.getSalary() > 50000) // 
                .filter(e -> "IT".equals(e.getDepartment())) // 
                .sorted(Comparator.comparingInt(Employee::getAge)) // 
                .limit(3) // 
                .forEach(e -> System.out.println(e.getName() + ", возраст: " + e.getAge())); // 
    }

    // Задача 3: Анализ заказов
    public static void task3(List<Order> orders) {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);

        // 1. Фильтр заказов
        List<Order> filteredOrders = orders.stream() //
                .filter(o -> o.getOrderDate().isAfter(oneMonthAgo)) //
                .filter(o -> o.getItems().stream()
                        .anyMatch(item -> "clothing".equals(item.getCategory()))) //
                .collect(Collectors.toList());

        // 2. Общая стоимость
        double totalCost = filteredOrders.stream()
                .flatMap(o -> o.getItems().stream()) //
                .mapToDouble(Item::getPrice)
                .sum(); // 

        System.out.println("Общая стоимость заказов: " + totalCost);

        // 3. Группировка по клиентам
        Map<String, List<Order>> ordersByCustomer = filteredOrders.stream()
                .collect(Collectors.groupingBy(Order::getCustomerId)); // 

        System.out.println("Заказы, сгруппированные по клиентам:");
        ordersByCustomer.forEach((customer, orderList) -> {
            System.out.println("Клиент: " + customer);
            orderList.forEach(order -> System.out.println("  - Заказ #" + order.getOrderId()));
        });
    }

    // Задача 4: Фильтрация товаров
    public static List<String> task4(List<Product> products) {
        return products.stream() // 
                .filter(p -> p.getPrice() > 50) // 
                .filter(p -> p.getQuantity() > 0) // 
                .sorted(Comparator.comparingInt(Product::getQuantity)) // 
                .map(Product::getName) // 
                .collect(Collectors.toList()); // 
    }

    // Задача 5: Анализ клиентов
    public static List<String> task5(List<Customer> customers) {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        LocalDate twoWeeksAgo = LocalDate.now().minusWeeks(2);

        return customers.stream() // 
                // 1. Фильтр клиентов, у которых сумма покупок за месяц > 1000
                .filter(c -> c.getPurchases().stream()
                        .filter(p -> p.getDate().isAfter(oneMonthAgo))
                        .mapToDouble(Purchase::getAmount)
                        .sum() > 1000)
                // 2. Увеличение суммы покупок "electronics" за 2 недели на 10%
                .peek(c -> c.getPurchases().stream()
                        .filter(p -> "electronics".equals(p.getCategory()))
                        .filter(p -> p.getDate().isAfter(twoWeeksAgo))
                        .forEach(p -> p.setAmount(p.getAmount() * 1.10)))
                // 3. Сортировка по кол-ву покупок
                .sorted(Comparator.comparingInt((Customer c) -> c.getPurchases().size()).reversed())
                // 4. Возвращение списка email
                .map(Customer::getEmail)
                .collect(Collectors.toList()); // 
    }
}