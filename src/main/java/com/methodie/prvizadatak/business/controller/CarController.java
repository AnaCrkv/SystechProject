package com.methodie.prvizadatak.business.controller;

import com.methodie.prvizadatak.business.service.CarService;
import com.methodie.prvizadatak.business.database.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    @Autowired
    CarService carService;
    @PostMapping ("/car")
    public ResponseEntity <Car> newCar (@RequestBody Car newCar)
    {
        return ResponseEntity.ok().body(carService.add(newCar));
    }
    @GetMapping ("car/{id}")
    public ResponseEntity<Car> one (@PathVariable Long id)
    {
        return ResponseEntity.ok().body(carService.find(id));
    }
    @PutMapping ("/car")
    public ResponseEntity<Car> car (@RequestBody Car newCar)
    {
        return ResponseEntity.ok().body(carService.update(newCar));
    }

    @GetMapping ("/car")
    public ResponseEntity <List<Car>> all()
    {
        return ResponseEntity.ok().body(carService.all());

    }

    @DeleteMapping("car/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id)
    {
        carService.delete(id);

        return ResponseEntity.ok().body("Success");
    }
}
