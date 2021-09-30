package com.methodie.prvizadatak.business.controller;

import com.methodie.prvizadatak.business.database.Car;
import com.methodie.prvizadatak.business.database.Customer;
import com.methodie.prvizadatak.business.service.CustomerService;
import com.methodie.prvizadatak.business.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.List;
@Controller
@RequestMapping("mvc")
public class CarMVCController {
    @Autowired
    CarService carService;
    @Autowired
    CustomerService customerService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {

                if(text.isEmpty()){
                    setValue(null);
                    return;
                }

                LocalDate date = LocalDate.parse(text);
                setValue(date);
            }

        });
    }
    @GetMapping("/cars")
    public String carList (Model  model)
    {
        List<Car> cars= carService.all();
        model.addAttribute("cars",cars);
        return "car1";
    }
    @GetMapping ("/newCar")
    public String showNewCar (Model model) {
        Car car=new Car();
        List<Customer> customers = customerService.all(Sort.by(new String[]{"surname", "name"}));
        model.addAttribute("car",car);
        model.addAttribute("customers", customers);
        return "newCar1";
    }
    @PostMapping ("/save1")
    public ModelAndView saveCar (Car car)
    {

        if (car.getManufacturer().isEmpty() || car.getKilometers().isEmpty() || car.getEngine() == null || car.getProduction_date()==null) {
            ModelAndView model1 = new ModelAndView("newCar1");
            model1.setViewName("newCar1");
            model1.addObject("car", car);
            model1.addObject("message","All the fields must be fulfilled!");
            List<Customer> customers = customerService.all(Sort.by(new String[]{"surname", "name"}));
            model1.addObject("customers", customers);

            return model1 ;
        }
        else {

            carService.add(car);
            ModelAndView model1 =  new ModelAndView("redirect:/mvc/cars");
            //model.setViewName("redirect:/mvc/customers");
            return model1;
        }
    }
    @GetMapping("/updateCar/{id}")
    public String updateCar1 (@PathVariable Long id,Model model1)
    {
        Car car=carService.find(id);
        List<Customer> customers=customerService.all(Sort.by(new String[]{"surname","name"}));
        model1.addAttribute("car",car);
        model1.addAttribute("customers",customers);
        return "updateCar1";

    }
    @PostMapping("/update1")
    public ModelAndView updateCar (Car car)
    {

        if (car.getManufacturer().isEmpty() || car.getKilometers().isEmpty() || car.getEngine() == null || car.getProduction_date()==null) {
            ModelAndView model1 = new ModelAndView("updateCar1");
            model1.setViewName("updateCar1");
            model1.addObject("car", car);
            model1.addObject("message","All the fields must be fulfilled!");
            List<Customer> customers = customerService.all(Sort.by(new String[]{"surname", "name"}));
            model1.addObject("customers", customers);

            return model1 ;
        }
        else {

            carService.update(car);
            ModelAndView model1 =  new ModelAndView("redirect:/mvc/cars");
            //model.setViewName("redirect:/mvc/customers");
            return model1;
        }
    }
    @GetMapping("/deleteCar/{id}")
    public String deleteCar (@PathVariable Long id, Model model3 )
    {
        List<Customer> customers = customerService.all(Sort.by(new String[]{"surname", "name"}));
        model3.addAttribute("customers", customers);
        Car car=carService.find(id);
        model3.addAttribute("car",car);
        return "deleteCar1";


    }
    @PostMapping("/delete1")
    public String deleteUpdate (@ModelAttribute ("car") Car car)
    {

        carService.delete(car.getId());
        return "redirect:/mvc/cars";
    }
}
