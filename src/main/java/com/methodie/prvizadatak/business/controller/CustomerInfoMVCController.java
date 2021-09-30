package com.methodie.prvizadatak.business.controller;

import com.methodie.prvizadatak.business.database.CustomerInfo;
import com.methodie.prvizadatak.business.database.Customer;
import com.methodie.prvizadatak.business.service.CustomerService;
import com.methodie.prvizadatak.business.service.CustomerInfoService;
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
public class CustomerInfoMVCController {
    @Autowired
    CustomerInfoService customerInfoService;
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

    @GetMapping("/customersInfo")
    public String allCustomerInfo (Model model)
    {
        List<CustomerInfo> customersInfo= customerInfoService.all();
        model.addAttribute("customersInfo",customersInfo);
        return "customerInfo1";
    }

    @GetMapping("/newCustomerInfo")
    public String newCustomerInfo (Model model)
    {
        CustomerInfo customerInfo=new CustomerInfo();
        List<Customer> customers = customerService.all(Sort.by(new String[]{"surname", "name"}));
        model.addAttribute("customers", customers);
        model.addAttribute("customerInfo", customerInfo);

        return "newCustomerInfo1";
    }
    @PostMapping("/save3")
    public ModelAndView saveCustomerInfo(CustomerInfo customerInfo)
    {
        if(customerInfo.getAmount()==null)
        {
            ModelAndView model1=new ModelAndView("newCustomerInfo1");
            model1.addObject("customerInfo",customerInfo);
            model1.addObject("message","All the fields must be fulfilled!");
            List<Customer> customers = customerService.all(Sort.by(new String[]{"surname", "name"}));
            model1.addObject("customers", customers);

            return model1 ;

        }
        else
        {
            customerInfoService.add(customerInfo);
            ModelAndView model1=new ModelAndView("redirect:/mvc/customersInfo");
            return model1;
        }

    }
    @GetMapping("updateCustomerInfo/{id}")
    public  String updateCustomerInfo (Model model,@PathVariable Long id)
    {
        CustomerInfo customerInfo= customerInfoService.find(id);
        List <Customer> customers = customerService.all(Sort.by(new String[]{"surname", "name"}));
        model.addAttribute("customers",customers);
        model.addAttribute("customerInfo",customerInfo);
        return "updateCustomerInfo1";

    }
    @PostMapping("/update3")
    public ModelAndView updateCustomerInfo(CustomerInfo customerInfo)
    {
        if( customerInfo.getAmount()==null)
        {
            ModelAndView model1=new ModelAndView("updateCustomerInfo1");
            model1.addObject("customerInfo",customerInfo);
            model1.addObject("message","All the fields must be fulfilled!");
            List<Customer> customers = customerService.all(Sort.by(new String[]{"surname", "name"}));
            model1.addObject("customers", customers);

            return model1 ;

        }
        else
        {
            customerInfoService.update(customerInfo);
            ModelAndView model1=new ModelAndView("redirect:/mvc/customersInfo");
            return model1;
        }

    }
    @GetMapping ("deleteCustomerInfo/{id}")
    public String deleteCustomerInfo(@PathVariable Long id, Model model3)
    {
        CustomerInfo customerInfo=customerInfoService.find(id);
        model3.addAttribute("customerInfo",customerInfo);
        List<Customer> customers = customerService.all(Sort.by(new String[]{"surname", "name"}));
        model3.addAttribute("customers", customers);
        return "deleteCustomerInfo1";
    }
    @PostMapping("/delete3")
    public String deleteUpdate (@ModelAttribute ("customer") CustomerInfo customerInfo)
    {

        customerInfoService.delete(customerInfo.getId());

        return "redirect:/mvc/customersInfo";
    }
}
