package com.methodie.prvizadatak.business.controller;

import com.methodie.prvizadatak.business.database.CustomerInfo;
import com.methodie.prvizadatak.business.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerInfoController {
    @Autowired
    CustomerInfoService customerInfoService;

    @PostMapping("/customerInfo")
    public ResponseEntity<CustomerInfo> newCustomerInfo (@RequestBody CustomerInfo newCustomerInfo)
    {
        return ResponseEntity.ok().body(customerInfoService.add(newCustomerInfo));
    }

    @GetMapping ("/customerInfo/{id}")
    public ResponseEntity<CustomerInfo> one (@PathVariable Long id)
    {
        return ResponseEntity.ok().body(customerInfoService.find(id));
    }


    @PutMapping ("/customerInfo")
    public ResponseEntity<CustomerInfo> customerInfo (@RequestBody CustomerInfo newCustomerInfo)
    {
        return ResponseEntity.ok().body(customerInfoService.update(newCustomerInfo));
    }
    @GetMapping ("/customerInfo")
    public ResponseEntity <List<CustomerInfo>> all()
    {
        return ResponseEntity.ok().body(customerInfoService.all());

    }
    @DeleteMapping("customerInfo/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id)
    {
        customerInfoService.delete(id);

        return ResponseEntity.ok().body("Success");
    }
}
