package com.methodie.prvizadatak.business.service;

import com.methodie.prvizadatak.business.database.CustomerInfo;

import java.util.List;
public interface CustomerInfoService {

    CustomerInfo add(CustomerInfo customerInfo);
    CustomerInfo find (Long id);
    CustomerInfo update (CustomerInfo customerInfo);
    List<CustomerInfo> all();
    void delete (Long id);
}
