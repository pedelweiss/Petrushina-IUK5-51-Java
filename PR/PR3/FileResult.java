
public record FileResult(long totalSalary, int employeeCount) {
    @Override
    public String toString() {
        return "Общая зарплата: " + totalSalary + ", Количество сотрудников: " + employeeCount;
    }
}