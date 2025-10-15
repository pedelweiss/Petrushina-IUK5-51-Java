import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RestaurantService {

    private final IngredientStore ingredientStore = new IngredientStore();
    // Отдельный пул потоков для выполнения асинхронных задач
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public CompletableFuture<Void> processOrder(Order order) {
        System.out.println("--- Принят новый заказ: " + order.dishName() + " ---");

        return CompletableFuture.supplyAsync(() -> {
                    // 1. Проверка наличия ингредиентов
                    if (!ingredientStore.takeIngredient(order.dishName())) {
                        throw new IngredientsUnavailableException("Недостаточно ингредиентов для: " + order.dishName());
                    }
                    return order;
                }, executor)
                .thenApplyAsync(o -> {
                    // 2. Приготовление блюда
                    System.out.println("Начинаем готовить: " + o.dishName());
                    try {
                        Thread.sleep(o.cookingTimeMs());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.println("Блюдо '" + o.dishName() + "' готово!");
                    return o;
                }, executor)
                .thenApply(o -> {
                    // 3. Расчёт стоимости (быстрая операция, можно без Async)
                    double finalPrice = o.price();
                    if (o.price() > 500) {
                        finalPrice *= 0.90; // Скидка 10%
                        System.out.println("Применена скидка 10%. Новая цена для '" + o.dishName() + "': " + String.format("%.2f", finalPrice));
                    }
                    return o.withNewPrice(finalPrice);
                })
                .thenAcceptAsync(o -> {
                    // 4. Оповещение о готовности
                    System.out.println("Уведомление: Ваш заказ '" + o.dishName() + "' готов! К оплате: " + String.format("%.2f", o.price()));
                }, executor)
                .exceptionally(ex -> {
                    // Обработка любых исключений в цепочке
                    System.err.println("ОШИБКА ОБРАБОТКИ ЗАКАЗА: " + ex.getMessage());
                    return null; // Завершаем CompletableFuture
                });
    }

    public void shutdown() throws InterruptedException {
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        RestaurantService restaurant = new RestaurantService();

        Order steakOrder = new Order("Стейк", 3000, 750);
        Order pastaOrder = new Order("Паста", 2000, 450);
        Order saladOrder = new Order("Салат", 1000, 300);
        Order failedSteakOrder = new Order("Стейк", 3000, 800); // Предположим, ингредиенты закончатся

        restaurant.processOrder(steakOrder);
        restaurant.processOrder(pastaOrder);
        restaurant.processOrder(saladOrder);

        // Дадим время на выполнение первых заказов
        Thread.sleep(1000);

        // Этот заказ, скорее всего, провалится, если ингредиентов было мало
        // Установим в IngredientStore начальное значение для Стейка = 1, чтобы продемонстрировать ошибку
        // restaurant.processOrder(failedSteakOrder);

        // Ждем достаточно времени, чтобы все асинхронные задачи завершились
        Thread.sleep(5000);
        restaurant.shutdown();
    }
}