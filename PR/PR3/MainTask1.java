import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MainTask1 {
    public static void main(String[] args) throws InterruptedException {

        List<String> filePaths = List.of("employees1.txt", "employees2.txt");

        ConcurrentMap<String, FileResult> results = new ConcurrentHashMap<>();

        List<Thread> threads = new ArrayList<>();

        // Создаем и запускаем по одному потоку на каждый файл
        for (String filePath : filePaths) {
            FileAnalyzer task = new FileAnalyzer(filePath, results);
            Thread thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("\n--- Результаты анализа ---");
        results.forEach((file, result) -> System.out.println(file + " -> " + result));
    }
}