import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Потокобезопасное хранилище ингредиентов
public class IngredientStore {
    private final Map<String, Integer> ingredients = new ConcurrentHashMap<>();

    public IngredientStore() {
        ingredients.put("Стейк", 10);
        ingredients.put("Паста", 15);
        ingredients.put("Салат", 20);
    }

    // Атомарная операция проверки и взятия ингредиента
    public boolean takeIngredient(String dishName) {
        // compute вернет null, если ключа нет, или новое значение.
        // Операция атомарна.
        return ingredients.computeIfPresent(dishName, (key, currentAmount) -> {
            if (currentAmount > 0) {
                System.out.println("Ингредиент для '" + key + "' есть в наличии.");
                return currentAmount - 1;
            } else {
                return 0;
            }
        }) != null && ingredients.get(dishName) >= 0; // Проверяем, что операция прошла успешно
    }
}