package com.methodie.prvizadatak.business.service;
import com.methodie.prvizadatak.business.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CustomerInfoRepository customerInfoRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;


    @Override
    public Customer add(Customer customer) {

        return customerRepository.save(customer);
    }
    @Override
    public Customer find (Long id)
    {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("error.customer_with_id_not_found"));
    }
    @Override
    public  Customer update(Customer customer)
    {
        Customer entity = customerRepository.findById(customer.getId()).orElseThrow(() -> new RuntimeException("error.customer_with_id_not_found"));


        if(entity.update(customer)) {

            return customerRepository.save(entity);
        } else {
            return entity;
        }

    }
    @Override
    @Transactional
    public void delete (Long id)
    {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("error.customer_with_id_not_found"));
        List<Car> cars = carRepository.findByCustomer(customer);
        List<CustomerInfo> customerInfo = customerInfoRepository.findByCustomer(customer) ;

        for(Car car : cars){
            List<Insurance> insurances = insuranceRepository.findByCar(car);
            insuranceRepository.deleteAll(insurances);
        }

        carRepository.deleteAll(cars);

        customerInfoRepository.deleteAll(customerInfo);
        customerRepository.delete(customer);

    }
    @Override
    public  List<Customer> all (Sort sort)
    {
        return customerRepository.findAll(sort);
    }
}
