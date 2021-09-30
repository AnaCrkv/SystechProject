package com.methodie.prvizadatak.business.database;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface CarRepository extends JpaRepository<Car,Long>{
    List<Car> findByCustomer(Customer customer);
}
