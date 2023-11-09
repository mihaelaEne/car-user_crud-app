package ro.mycode.usercar.service;

import ro.mycode.usercar.car.repository.CarRepo;

public class CarService {

    private CarRepo carRepo;



    public CarService(CarRepo carRepo){
        this.carRepo=carRepo;
    }

    
}
