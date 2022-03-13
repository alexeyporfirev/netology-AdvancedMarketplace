package marketplace;

import cars.Car;
import java.util.ArrayList;
import java.util.List;

public class Marketplace {

    private int carWaitingTime;
    private int carManufacturingTime;
    private Seller seller;
    private List<Car> cars;
    private int counter;
    private boolean isSalesStopped;

    /**
     * Создание объекта магазина по продаже автомобилей
     * @param carWaitingTime Время ожидания клиента
     * @param carManufacturingTime Время изгтовления нового автомобиля
     */
    public Marketplace(int carWaitingTime, int carManufacturingTime) {
        this.seller = new Seller(this);
        this.cars = new ArrayList<Car>();
        this.carWaitingTime = carWaitingTime;
        this.carManufacturingTime = carManufacturingTime;
    }

    /**
     * Продажа автомобиля
     */
    public void sellCar() {
        seller.sellCar();
    }

    /**
     * Получение автомобиля от производителя
     */
    public void acceptCar() {
        seller.receiveCar();
    }

    /**
     * Получение списка доступных для продажи автомобилей
     * @return Список доступных для продажи автомобилей
     */
    public List<Car> getCars() {
        return this.cars;
    }

    /**
     * Получение времени ожидания клиента
     * @return Время ожидания клиента
     */
    public int getCarWaitingTime() {
        return carWaitingTime;
    }

    /**
     * Получение времени изгтовления нового автомобиля
     * @return Время изгтовления нового автомобиля
     */
    public int getCarManufacturingTime() {
        return carManufacturingTime;
    }

    /**
     * Получени кол-ва проданных автомобилей
     * @return
     */
    public int getCounter() {
        return this.counter;
    }

    /**
     * Увеличение количества проданных автомобилей на 1
     */
    public void increaseCounter() {
        this.counter++;
    }

    /**
     * Изменения статуса остановки продаж
     * @param salesStopped новый статус остановки продаж
     */
    public void setSalesStopped(boolean salesStopped) {
        isSalesStopped = salesStopped;
    }

    /**
     * Проверка остановлены ли продажи
     * @return true если продажи остановлены
     */
    public boolean isSalesStopped() {
        return isSalesStopped;
    }
}
