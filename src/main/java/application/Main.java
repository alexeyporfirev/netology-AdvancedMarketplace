package application;

import marketplace.Marketplace;


public class Main {

    private static final int CAR_WAITING_TIME = 1000;
    private static final int CAR_MANUFACTURING_TIME = 500;
    private static final int CARS_SOLD = 10;


    public static void main(String[] args) throws InterruptedException {

        Marketplace shop = new Marketplace(CAR_WAITING_TIME, CAR_MANUFACTURING_TIME);

        Thread customer1 =  new Thread(null, shop::sellCar, "Покупатель 1");
        Thread customer2 =  new Thread(null, shop::sellCar, "Покупатель 2");
        Thread customer3 =  new Thread(null, shop::sellCar, "Покупатель 3");
        Thread manufacturer = new Thread(null, shop::acceptCar, "BMW");

        customer1.start();
        customer2.start();
        customer3.start();
        manufacturer.start();

        /* Ждем, пока не будет продано 10 автомобилей
         *
         */
        while(shop.getCounter() != CARS_SOLD) {
            Thread.sleep((long)(CAR_WAITING_TIME * 0.9));
        }

        shop.setSalesStopped(true);
        customer1.interrupt();
        customer2.interrupt();
        customer3.interrupt();
        manufacturer.interrupt();

    }
}
