package com.methodie.prvizadatak.business.service;
import com.methodie.prvizadatak.business.database.Insurance;
import com.methodie.prvizadatak.business.database.Car;
import com.methodie.prvizadatak.business.database.InsuranceRepository;
import com.methodie.prvizadatak.business.database.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
public class InsuranceServiceImpl implements InsuranceService {
    @Autowired
    InsuranceRepository insuranceRepository;
    @Autowired
    CarRepository carRepository;
    @Override
    public Insurance add(Insurance insurance)
    {
        return insuranceRepository.save(insurance);
    }

    @Override
    public Insurance find(Long id)
    {
        return insuranceRepository.findById(id).orElseThrow(() -> new RuntimeException("error.customer_with_id_not_found"));
    }
    private void initializeEntities(Insurance temp, Insurance item){

        if (item != null && (temp == null || !Objects.equals(temp.getCar(), item.getCar()))) {
            item.setCar(carRepository.findById(item.getCar().getId())
                    .orElseThrow(
                            () -> new RuntimeException("error.No_record")
                    ));
        }

    }
    @Override
    public  Insurance update(Insurance insurance) {
        Insurance entity = insuranceRepository.findById(insurance.getId()).orElseThrow(() -> new RuntimeException("error.customer_with_id_not_found"));
        initializeEntities(entity, insurance);

        if (entity.update(insurance)) {

            return insuranceRepository.save(entity);
        } else {
            return entity;
        }
    }
    @Override
    public List<Insurance> all() {
        return insuranceRepository.findAll();
    }

    @Override
    public List<Insurance> getByCar(Car car) {
        return insuranceRepository.findByCar(car);
    }

    @Override
    public void delete (Long id)
    {
        insuranceRepository.deleteById(id);
    }

}
