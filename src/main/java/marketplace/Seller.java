package marketplace;

import cars.Car;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Seller {

    private Marketplace shop;
    private ReentrantLock lock;
    private Condition condition;

    /**
     * Создание нового продавца, привязанного к магазину
     *
     * @param shop Объект магазина
     */
    public Seller(Marketplace shop) {
        this.shop = shop;
        lock = new ReentrantLock(true);
        condition = lock.newCondition();
    }

    /**
     * Продажа автомобиля
     */
    public void sellCar() {
            while (!Thread.currentThread().isInterrupted() && !shop.isSalesStopped()) {
                try {
                    Thread.sleep(shop.getCarWaitingTime());
                    lock.lock();
                    System.out.println("Покупатель " + Thread.currentThread().getName() + " зашел в магазин.");
                    while (shop.getCars().size() == 0) {
                        System.out.println("Машин нет");
                        condition.await();
                    }
                    System.out.println("Покупатель " + Thread.currentThread().getName() + " уехал на новеньком авто [" +
                            shop.getCars().get(0).getModel() + "].");
                    shop.getCars().remove(0);
                    shop.increaseCounter();
                    condition.signal();
                } catch (InterruptedException e) {
                    System.out.println("Магазин закончил продажи!");
                } finally {
                    if(lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }
    }

    /**
     * Получение автомобиля от производителя
     */
    public void receiveCar() {
        while (!Thread.currentThread().isInterrupted() && !shop.isSalesStopped()) {
            try {
                Thread.sleep(shop.getCarManufacturingTime());
                lock.lock();
                Car car = new Car("BMW_X5", 2022);
                shop.getCars().add(car);
                System.out.println("Производитель " + Thread.currentThread().getName() + " выпустил 1 авто [" +
                        car.getModel() + "].");
                condition.signal();
            } catch (InterruptedException e) {
                System.out.println("Магазин закончил продажи!");
            } finally {
                if(lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }
}
