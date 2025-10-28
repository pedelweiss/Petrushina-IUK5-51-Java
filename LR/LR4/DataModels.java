import java.time.LocalDate;
import java.util.List;
import java.util.Map;

// --- Для Задачи 1 ---
class Student {
    String name;
    int age;
    Map<String, Integer> grades; // предмет -> оценка

    public Student(String name, int age, Map<String, Integer> grades) {
        this.name = name;
        this.age = age;
        this.grades = grades;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public Map<String, Integer> getGrades() { return grades; }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}

// --- Для Задачи 2 ---
class Employee {
    String name;
    int age;
    String department;
    double salary;

    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', age=" + age + ", department='" + department + "'}";
    }
}

// --- Для Задачи 3 ---
class Item {
    String category;
    double price;

    public Item(String category, double price) {
        this.category = category;
        this.price = price;
    }

    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Item{category='" + category + "', price=" + price + "}";
    }
}

class Order {
    int orderId;
    List<Item> items;
    LocalDate orderDate;
    String customerId;

    public Order(int orderId, List<Item> items, LocalDate orderDate, String customerId) {
        this.orderId = orderId;
        this.items = items;
        this.orderDate = orderDate;
        this.customerId = customerId;
    }

    public int getOrderId() { return orderId; }
    public List<Item> getItems() { return items; }
    public LocalDate getOrderDate() { return orderDate; }
    public String getCustomerId() { return customerId; }

    @Override
    public String toString() {
        return "Order{orderId=" + orderId + ", items=" + items.size() + ", customerId='" + customerId + "'}";
    }
}

// --- Для Задачи 4 ---
class Product {
    String name;
    double price;
    int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + ", quantity=" + quantity + "}";
    }
}

// --- Для Задачи 5 ---
class Purchase {
    String category;
    double amount;
    LocalDate date;

    public Purchase(String category, double amount, LocalDate date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; } // Сеттер нужен для задачи 5
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return "Purchase{category='" + category + "', amount=" + amount + ", date=" + date + "}";
    }
}

class Customer {
    String email;
    List<Purchase> purchases;

    public Customer(String email, List<Purchase> purchases) {
        this.email = email;
        this.purchases = purchases;
    }

    public String getEmail() { return email; }
    public List<Purchase> getPurchases() { return purchases; }

    @Override
    public String toString() {
        return "Customer{email='" + email + "', purchases=" + purchases.size() + "}";
    }
}