public class LR1 {

    public static void main(String[] args) {
        System.out.println("======= Демонстрация Условий =======");
        demonstrateConditions();

        System.out.println("\n======= Демонстрация Switch/Case =======");
        demonstrateSwitch();

        System.out.println("\n======= Демонстрация Циклов =======");
        demonstrateLoops();

        System.out.println("\n======= Демонстрация Break и Continue =======");
        demonstrateBreakAndContinue();

        System.out.println("\n======= Демонстрация Многомерного массива =======");
        demonstrateMultiArray();

        System.out.println("\n======= Демонстрация Boxing/Unboxing =======");
        demonstrateBoxing();

        System.out.println("\n======= Демонстрация Манипуляций со строками =======");
        demonstrateStringManipulation();

        System.out.println("\n======= Демонстрация Сравнения строк =======");
        demonstrateStringComparison();

        System.out.println("\n======= Демонстрация StringBuilder =======");
        demonstrateStringBuilder();
    }

    public static void demonstrateConditions() {
        int a = 10;
        int b = -5;
        int c = 0;
        int d = 20;

        // Пример с двумя операндами (&&)
        System.out.println("--- Пример с 2 операндами ---");
        if (a > 0 && d > 0) {
            System.out.println("a и d оба положительные.");
        } else {
            System.out.println("Хотя бы одно из чисел a или d не является положительным.");
        }

        // Пример с тремя операндами (&&, ||)
        System.out.println("\n--- Пример с 3 операндами ---");
        if (a > 0 && b > 0 || c == 0) {
            System.out.println("Либо a и b положительные, либо c равно нулю.");
        } else if (d > 15) {
            System.out.println("d больше 15.");
        } else {
            System.out.println("Ни одно из вышеперечисленных условий не выполнилось.");
        }

        // Пример с четырьмя операндами
        System.out.println("\n--- Пример с 4 операндами ---");
        if ((a > 0 || b > 0) && (c == 0 && d > 15)) {
            System.out.println("Условие с четырьмя операндами выполнено.");
        } else {
            System.out.println("Условие с четырьмя операндами не выполнено.");
        }
    }

    public static void demonstrateSwitch() {
        int dayOfWeek = 3;
        String dayName;

        switch (dayOfWeek) {
            case 1:
                dayName = "Понедельник";
                break; // Выход из switch
            case 2:
                dayName = "Вторник";
                break;
            case 3:
                dayName = "Среда";
                break;
            case 4:
                dayName = "Четверг";
                break;
            case 5:
                dayName = "Пятница";
                break;
            default: // Если ни один case не совпал
                dayName = "Выходной";
                break;
        }
        System.out.println("Сегодня: " + dayName); // Выведет: Сегодня: Среда
    }

    public static void demonstrateLoops() {
        // Цикл for
        System.out.println("--- Цикл for ---");
        for (int i = 0; i < 5; i++) {
            System.out.println("Итерация: " + i);
        }

        // Цикл while
        System.out.println("\n--- Цикл while ---");
        int j = 0;
        while (j < 5) {
            System.out.println("Итерация: " + j);
            j++;
        }

        // Цикл do...while
        System.out.println("\n--- Цикл do...while ---");
        int k = 0;
        do {
            System.out.println("Итерация: " + k);
            k++;
        } while (k < 5);
    }

    public static void demonstrateBreakAndContinue() {
        System.out.println("--- Демонстрация break и continue ---");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 != 0) {
                continue; // Пропустить нечетные числа, перейти к следующей итерации
            }
            if (i > 8) {
                break; // Выйти из цикла, если i стало больше 8
            }
            System.out.println("Четное число: " + i);
        }
    }

    public static void demonstrateMultiArray() {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        System.out.println("--- Перебор элементов двумерного массива ---");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void demonstrateBoxing() {
        // --- int/Integer ---
        System.out.println("--- int/Integer ---");
        int primitiveInt = 100;

        // Упаковка (Boxing)
        Integer wrapperIntAuto = primitiveInt; // Автоматическая упаковка (Autoboxing)
        Integer wrapperIntExplicit = Integer.valueOf(primitiveInt); // Явная упаковка
        System.out.println("Упаковка: " + wrapperIntAuto + " (auto), " + wrapperIntExplicit + " (explicit)");

        // Распаковка (Unboxing)
        int unboxedIntAuto = wrapperIntAuto;      // Автоматическая распаковка (Unboxing)
        int unboxedIntExplicit = wrapperIntExplicit.intValue(); // Явная распаковка
        System.out.println("Распаковка: " + unboxedIntAuto + " (auto), " + unboxedIntExplicit + " (explicit)\n");

        // --- boolean/Boolean ---
        System.out.println("--- boolean/Boolean ---");
        boolean primitiveBool = true;

        // Упаковка (Boxing)
        Boolean wrapperBoolAuto = primitiveBool; // Autoboxing
        Boolean wrapperBoolExplicit = Boolean.valueOf(primitiveBool); // Явная упаковка
        System.out.println("Упаковка: " + wrapperBoolAuto + " (auto), " + wrapperBoolExplicit + " (explicit)");

        // Распаковка (Unboxing)
        boolean unboxedBoolAuto = wrapperBoolAuto;   // Unboxing
        boolean unboxedBoolExplicit = wrapperBoolExplicit.booleanValue(); // Явная распаковка
        System.out.println("Распаковка: " + unboxedBoolAuto + " (auto), " + unboxedBoolExplicit + " (explicit)\n");

        // --- long/Long ---
        System.out.println("--- long/Long ---");
        long primitiveLong = 123456789L;

        // Упаковка (Boxing)
        Long wrapperLongAuto = primitiveLong; // Autoboxing
        Long wrapperLongExplicit = Long.valueOf(primitiveLong); // Явная упаковка
        System.out.println("Упаковка: " + wrapperLongAuto + " (auto), " + wrapperLongExplicit + " (explicit)");

        // Распаковка (Unboxing)
        long unboxedLongAuto = wrapperLongAuto;   // Unboxing
        long unboxedLongExplicit = wrapperLongExplicit.longValue(); // Явная распаковка
        System.out.println("Распаковка: " + unboxedLongAuto + " (auto), " + unboxedLongExplicit + " (explicit)\n");

        // --- Ситуация с NullPointerException ---
        System.out.println("--- NullPointerException Demo ---");
        Integer nullableInteger = null;
        try {
            int number = nullableInteger;
        } catch (NullPointerException e) {
            System.err.println("Произошла ошибка: " + e);
        }
    }

    public static void demonstrateStringManipulation() {
        String original = "Java-это-весело-и-мощно";

        // 1. Замена в строке
        String replaced = original.replace("-", " ");
        System.out.println("Замена: " + replaced);

        // 2. Обрезка строки (получение подстроки)
        String substring = replaced.substring(9, 15); // символы с 9 по 14
        System.out.println("Обрезка: " + substring);

        // 3. Разбиение строки на подстроки по разделителю
        String[] parts = original.split("-");
        System.out.println("Разбиение:");
        for (String part : parts) {
            System.out.println("  - " + part);
        }
    }

    public static void demonstrateStringComparison() {
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");
        String s4 = "hello";

        System.out.println("--- Сравнение 'Hello' и 'Hello' ---");
        System.out.println("s1 == s2: " + (s1 == s2));
        System.out.println("s1.equals(s2): " + s1.equals(s2));

        System.out.println("\n--- Сравнение 'Hello' из пула и нового объекта ---");
        System.out.println("s1 == s3: " + (s1 == s3));
        System.out.println("s1.equals(s3): " + s1.equals(s3));

        System.out.println("\n--- Сравнение с игнорированием регистра ---");
        System.out.println("s1.equals(s4): " + s1.equals(s4));
        System.out.println("s1.equalsIgnoreCase(s4): " + s1.equalsIgnoreCase(s4));
    }

    public static void demonstrateStringBuilder() {
        StringBuilder sb = new StringBuilder("Привет");

        // 1. Конкатенация (добавление строк)
        sb.append(", ");
        sb.append("мир");
        sb.append("!");
        System.out.println("После конкатенации: " + sb.toString());

        // 2. Обрезка (удаление части строки) (символы с индекса 6 по 7)
        sb.delete(6, 8);
        System.out.println("После обрезки: " + sb.toString());
    }
}