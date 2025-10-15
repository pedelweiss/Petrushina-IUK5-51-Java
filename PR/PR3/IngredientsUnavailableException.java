// Пользовательское исключение
class IngredientsUnavailableException extends RuntimeException {
    public IngredientsUnavailableException(String message) {
        super(message);
    }
}