package com.methodie.prvizadatak.business.service;

import com.methodie.prvizadatak.business.database.Car;
import java.util.List;

public interface CarService {
    Car add (Car car);
    Car find (Long id);
    Car update(Car car);
    void delete (Long id);
    List <Car> all ();
}
