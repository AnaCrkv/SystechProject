package com.methodie.prvizadatak.business.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerInfoRepository extends JpaRepository<CustomerInfo,Long> {
    List<CustomerInfo> findByCustomer (Customer customer);
}
