package src.financialassistant.service;

public interface DataStorageService {
    // Метод для сохранения всех данных
    void saveData(DataContainer data);

    // Метод для загрузки всех данных
    DataContainer loadData();
}
