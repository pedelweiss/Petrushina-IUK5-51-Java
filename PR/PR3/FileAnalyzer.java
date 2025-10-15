import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentMap;

public class FileAnalyzer implements Runnable {
    private final String filePath;
    private final ConcurrentMap<String, FileResult> results;

    public FileAnalyzer(String filePath, ConcurrentMap<String, FileResult> results) {
        this.filePath = filePath;
        this.results = results;
    }

    @Override
    public void run() {
        long totalSalary = 0;
        int employeeCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        totalSalary += Long.parseLong(parts[2].trim());
                        employeeCount++;
                    } catch (NumberFormatException e) {
                        System.err.println("Неверный формат зарплаты в файле " + filePath + ": " + line);
                    }
                }
            }

            results.put(filePath, new FileResult(totalSalary, employeeCount));
            System.out.println("Файл " + filePath + " обработан.");
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла " + filePath + ": " + e.getMessage());
        }
    }
}