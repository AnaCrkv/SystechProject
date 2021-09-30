package com.methodie.prvizadatak.business.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InsuranceRepository extends JpaRepository <Insurance,Long>{
    @Query("select i from Insurance i where i.car = :car")
    List<Insurance> findByCarHql(Car car);
    List<Insurance> findByCar(Car car);

}
