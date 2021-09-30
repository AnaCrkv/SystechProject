package com.methodie.prvizadatak.business.service;

import com.methodie.prvizadatak.business.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CustomerInfoRepository customerInfoRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;

    @Override
    public Car add (Car car)
    {
        return carRepository.save(car);
    }
    @Override
    public Car find (Long id)
    {
        return carRepository.findById(id).orElseThrow(()-> new RuntimeException("error.car_with_id_not_found"));
    }

    private void initializeEntities(Car temp, Car item){

        if (item != null && (temp == null || !Objects.equals(temp.getCustomer(), item.getCustomer()))) {
            item.setCustomer(customerRepository.findById(item.getCustomer().getId())
                    .orElseThrow(
                            () -> new RuntimeException("error.No_record")
                    ));
        }

    }
    @Override
    public  Car update(Car car) {
        Car entity = carRepository.findById(car.getId()).orElseThrow(() -> new RuntimeException("error.customer_with_id_not_found"));
        initializeEntities(entity, car);

        if (entity.update(car)) {

            return carRepository.save(entity);
        } else {
            return entity;
        }
    }

    @Override
    @Transactional
    public void  delete(Long id)
    {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("error.car_with_id_not_found"));

        List<Insurance> insurances = insuranceRepository.findByCar(car);
        insuranceRepository.deleteAll(insurances);
        carRepository.delete(car);
    }

    @Override
    public  List<Car> all ()
    {
        return carRepository.findAll();
    }


}
