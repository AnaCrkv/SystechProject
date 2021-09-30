package com.methodie.prvizadatak.business.service;

import com.methodie.prvizadatak.business.database.Car;
import com.methodie.prvizadatak.business.database.Insurance;

import java.util.List;

public interface InsuranceService {
    Insurance add(Insurance insurance);
    Insurance find (Long id);
    Insurance update (Insurance insurance);
    List<Insurance> all();
    List<Insurance> getByCar(Car car);
    void delete (Long id);
}
