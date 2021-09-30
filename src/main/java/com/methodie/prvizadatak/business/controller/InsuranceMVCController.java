package com.methodie.prvizadatak.business.controller;

import com.methodie.prvizadatak.business.database.Car;
import com.methodie.prvizadatak.business.database.Insurance;
import com.methodie.prvizadatak.business.service.CarService;
import com.methodie.prvizadatak.business.service.InsuranceService;
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
public class InsuranceMVCController {
    @Autowired
    CarService carService;
    @Autowired
    InsuranceService insuranceService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {

                if (text.isEmpty()) {
                    setValue(null);
                    return;
                }

                LocalDate date = LocalDate.parse(text);
                setValue(date);
            }

        });
    }

    @GetMapping("/insurances")
    public String InsuranceList(Model model) {
        List<Insurance> insurances = insuranceService.all();
        model.addAttribute("insurances", insurances);
        return "insurance";
    }

    @GetMapping("/newInsurances")
    public String newInsurance(Model model) {
        Insurance insurance = new Insurance();
        List<Car> cars = carService.all();
        model.addAttribute("insurance", insurance);
        model.addAttribute("cars", cars);
        return "newInsurance1";

    }
    @PostMapping ("/save2")
    public ModelAndView saveInsurance (Insurance insurance)
    {

        if (insurance.getStartDate()==null || insurance.getEndDate()==null || insurance.getPrice()==null) {
            ModelAndView model1 = new ModelAndView("newInsurance1");
            // model1.setViewName("newInsurance1");
            model1.addObject("insurance",insurance);
            model1.addObject("message","All fields must be fulfilled!");
            List<Car> cars= carService.all();
            model1.addObject("cars",cars);
            return model1 ;
        }
        else {

            insuranceService.add(insurance);
            ModelAndView model1 =  new ModelAndView("redirect:/mvc/insurances");
            //model.setViewName("redirect:/mvc/customers");
            return model1;
        }
    }
    @GetMapping("/updateInsurance/{id}")
    public String updateInsurance (@PathVariable Long id,Model model1)
    {
        Insurance insurance=insuranceService.find(id);
        List<Car> cars=carService.all();
        model1.addAttribute("cars",cars);
        model1.addAttribute("insurance",insurance);
        return "updateInsurance1";

    }
    @PostMapping("/update2")
    public ModelAndView updateInsurance1 (Insurance insurance)
    {
        if (insurance.getStartDate()==null || insurance.getEndDate()==null || insurance.getPrice()==null) {
            ModelAndView model1 = new ModelAndView("updateInsurance1");
            // model1.setViewName("newInsurance1");
            model1.addObject("insurance",insurance);
            model1.addObject("message","All fields must be fulfilled!");
            List<Car> cars= carService.all();
            model1.addObject("cars",cars);
            return model1 ;
        }
        else {

            insuranceService.update(insurance);
            ModelAndView model1 =  new ModelAndView("redirect:/mvc/insurances");
            return model1;
        }

    }
    @GetMapping("/deleteInsurance/{id}")
    public String deleteCar (@PathVariable Long id, Model model3 )
    {
        List<Car> cars = carService.all();
        model3.addAttribute("cars", cars);
        Insurance insurance=insuranceService.find(id);
        model3.addAttribute("insurance",insurance);
        return "deleteInsurance1";


    }
    @PostMapping("/delete2")
    public String deleteUpdate (@ModelAttribute ("insurance") Insurance insurance)
    {

        insuranceService.delete(insurance.getId());
        return "redirect:/mvc/insurances";
    }
}
