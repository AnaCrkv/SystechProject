package com.methodie.prvizadatak.business.service;

import com.methodie.prvizadatak.business.database.Customer;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CustomerService {
    Customer add (Customer customer);
    Customer find (Long id);
    Customer update(Customer customer);
    void delete (Long id);
    List <Customer> all (Sort sort);
}
