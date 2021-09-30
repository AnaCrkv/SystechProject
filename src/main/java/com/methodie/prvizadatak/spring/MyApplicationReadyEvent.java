package com.methodie.prvizadatak.spring;
import com.methodie.prvizadatak.business.database.enums.Active;
import com.methodie.prvizadatak.business.database.enums.EGender;
import com.methodie.prvizadatak.business.database.enums.Type;
import com.methodie.prvizadatak.business.database.enums.EngineType;
import com.methodie.prvizadatak.business.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class MyApplicationReadyEvent implements  ApplicationListener<ApplicationReadyEvent>{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private CustomerInfoRepository customerInfoRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        populateDb();

    }

    private void populateDb(){

        Customer customer = new Customer();
        customer.setName("John");
        customer.setSurname("Wick");
        customer.setDate(LocalDate.of(2020, 6, 1));
        customer.setGender(EGender.MALE);


        customerRepository.save(customer);

        System.out.println(customer.getDate().toString());

        Customer customer1= new Customer();
        customer1.setName("Anna");
        customer1.setSurname("Anderson");
        customer1.setDate(LocalDate.of(2020,5,31));
        customer1.setGender(EGender.FEMALE);

        customerRepository.save(customer1);

        Customer customer2= new Customer();
        customer2.setName("Paul");
        customer2.setSurname("Walker");
        customer2.setDate(LocalDate.of(2020,10,5));
        customer2.setGender(EGender.MALE);

        customerRepository.save(customer2);

        Customer customer3= new Customer();
        customer3.setName("Michael");
        customer3.setSurname("Douglas");
        customer3.setDate(LocalDate.of(2020,11,6));
        customer3.setGender(EGender.MALE);

        customerRepository.save(customer3);

        Car car= new Car();
        car.setManufacturer("BMW");
        car.setProduction_date(LocalDate.of(2005,5,20));
        car.setEngine(1400);
        car.setEngine_type(EngineType.DieselEngine);
        car.setKilometers("40000");
        car.setCustomer(customer);

        carRepository.save(car);

        Car car1= new Car();
        car1.setManufacturer("Mercedes");
        car1.setProduction_date(LocalDate.of(2003,6,10));
        car1.setEngine(2000);
        car1.setEngine_type(EngineType.GasEngine);
        car1.setKilometers("20000");
        car1.setCustomer(customer1);

        carRepository.save(car1);

        Car car2= new Car();
        car2.setManufacturer("Volvo");
        car2.setProduction_date(LocalDate.of(2007,3,23));
        car2.setEngine(1200);
        car2.setEngine_type(EngineType.ElectricEngines);
        car2.setKilometers("10000");
        car2.setCustomer(customer2);

        carRepository.save(car2);

        Car car3= new Car();
        car3.setManufacturer("Peugeot");
        car3.setProduction_date(LocalDate.of(2002,7,29));
        car3.setEngine(1600);
        car3.setEngine_type(EngineType.DieselEngine);
        car3.setKilometers("30000");
        car3.setCustomer(customer3);

        carRepository.save(car3);

        Insurance insurance= new Insurance();
        insurance.setStartDate(LocalDate.of(2020,3, 1));
        insurance.setEndDate(LocalDate.of(2021, 3,5));
        insurance.setType(Type.LIABILITY_COVERAGE);
        insurance.setPrice(600);
        insurance.setCar(car);

        insuranceRepository.save(insurance);

        Insurance insurance1= new Insurance();
        insurance1.setStartDate(LocalDate.of(2020,5, 15));
        insurance1.setEndDate(LocalDate.of(2021, 5,20));
        insurance1.setType(Type.COLLISION_COVERAGE);
        insurance1.setPrice(800);
        insurance1.setCar(car1);

        insuranceRepository.save(insurance1);

        Insurance insurance2= new Insurance();
        insurance2.setStartDate(LocalDate.of(2020,8, 10));
        insurance2.setEndDate(LocalDate.of(2021, 8,15));
        insurance2.setType(Type.PERSONAL_INJURY_PROTECTION);
        insurance2.setPrice(900);
        insurance2.setCar(car2);

        insuranceRepository.save(insurance2);

        Insurance insurance3= new Insurance();
        insurance3.setStartDate(LocalDate.of(2020,10, 12));
        insurance3.setEndDate(LocalDate.of(2021, 10,17));
        insurance3.setType(Type.UNINSURED_COVERAGE);
        insurance3.setPrice(600);
        insurance3.setCar(car3);

        insuranceRepository.save(insurance3);

        CustomerInfo customerInfo= new CustomerInfo();
        customerInfo.setActivation(Active.Active);
        customerInfo.setAmount(300);
        customerInfo.setCustomer(customer);

        customerInfoRepository.save(customerInfo);

        CustomerInfo customerInfo1= new CustomerInfo();
        customerInfo1.setActivation(Active.Active);
        customerInfo1.setAmount(450);
        customerInfo1.setCustomer(customer1);

        customerInfoRepository.save(customerInfo1);

        CustomerInfo customerInfo2= new CustomerInfo();
        customerInfo2.setActivation(Active.UnActive);
        customerInfo2.setAmount(600);
        customerInfo2.setCustomer(customer2);

        customerInfoRepository.save(customerInfo2);

        CustomerInfo customerInfo3= new CustomerInfo();
        customerInfo3.setActivation(Active.UnActive);
        customerInfo3.setAmount(800);
        customerInfo3.setCustomer(customer3);

        customerInfoRepository.save(customerInfo3);

    }
}
