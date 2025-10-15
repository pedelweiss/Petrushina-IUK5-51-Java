// Класс заказа
public record Order(String dishName, int cookingTimeMs, double price) {
    public Order withNewPrice(double newPrice) {
        return new Order(this.dishName, this.cookingTimeMs, newPrice);
    }
}