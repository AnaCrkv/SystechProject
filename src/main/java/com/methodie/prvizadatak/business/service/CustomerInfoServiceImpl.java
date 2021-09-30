package com.methodie.prvizadatak.business.service;

import com.methodie.prvizadatak.business.database.CustomerInfo;
import com.methodie.prvizadatak.business.database.CustomerInfoRepository;
import com.methodie.prvizadatak.business.database.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
@Service
public class CustomerInfoServiceImpl implements CustomerInfoService{
    @Autowired
    CustomerInfoRepository customerInfoRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerInfo add(CustomerInfo customerInfo) {
        return customerInfoRepository.save(customerInfo);
    }

    @Override
    public CustomerInfo find(Long id) {
        return customerInfoRepository.findById(id).orElseThrow(()-> new RuntimeException("error.car_with_id_not_found"));
    }
    private void initializeEntities(CustomerInfo temp, CustomerInfo item){

        if (item != null && (temp == null || !Objects.equals(temp.getCustomer(), item.getCustomer()))) {
            item.setCustomer(customerRepository.findById(item.getCustomer().getId())
                    .orElseThrow(
                            () -> new RuntimeException("error.No_record")
                    ));
        }

    }

    @Override
    public CustomerInfo update(CustomerInfo customerInfo) {
        CustomerInfo entity = customerInfoRepository.findById(customerInfo.getId()).orElseThrow(() -> new RuntimeException("error.customer_with_id_not_found"));
        initializeEntities(entity,customerInfo);

        if(entity.update(customerInfo)) {

            return customerInfoRepository.save(entity);
        } else {
            return entity;
        }
    }

    @Override
    public List<CustomerInfo> all() {
        return customerInfoRepository.findAll();
    }
    @Override
    public void delete (Long id)
    {
        customerInfoRepository.deleteById(id);
    }
}
