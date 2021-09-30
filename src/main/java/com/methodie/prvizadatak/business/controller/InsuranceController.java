package com.methodie.prvizadatak.business.controller;
import com.methodie.prvizadatak.business.database.Insurance;
import com.methodie.prvizadatak.business.database.Car;
import com.methodie.prvizadatak.business.service.InsuranceService;
import com.methodie.prvizadatak.business.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InsuranceController {
    @Autowired
    InsuranceService insuranceService;
    @Autowired
    CarService carService;
    @PostMapping("/insurance")
    public ResponseEntity<Insurance> newInsurance (@RequestBody Insurance newInsurance)
    {
        return ResponseEntity.ok().body(insuranceService.add(newInsurance));
    }

    @GetMapping ("/insurance/{id}")
    public ResponseEntity <Insurance> ins (@PathVariable Long id)
    {
        return ResponseEntity.ok().body(insuranceService.find(id));


    }

    @PutMapping ("/insurance")
    public ResponseEntity <Insurance> insurance (@RequestBody Insurance newInsurance)
    {
        return ResponseEntity.ok().body(insuranceService.update(newInsurance));

    }

    @GetMapping ("/insurance")
    public ResponseEntity <List<Insurance>> all()
    {
        return ResponseEntity.ok().body(insuranceService.all());

    }
    @DeleteMapping("insurance/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id)
    {
        insuranceService.delete(id);

        return ResponseEntity.ok().body("Success");
    }
}
