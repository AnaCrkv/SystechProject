package com.methodie.prvizadatak.business.controller;

import com.methodie.prvizadatak.business.database.Customer;
import com.methodie.prvizadatak.business.service.CustomerService;

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
public class CustomerMVCController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public String customerList(Model model) {
        List<Customer> list=customerService.all(Sort.by("name","surname"));
        model.addAttribute("list",list);
        return "customer1";

    }

    @GetMapping ("/newCustomer")
    public String showNewCustomer (Model model) {
        Customer customer=new Customer();
        model.addAttribute("customer",customer);
        return "newCustomer1";
    }

    @PostMapping ("/save")
    public ModelAndView saveCustomer (Customer customer) {

        //  if(customer.getName().equals("")) {
        //     return new ModelAndView("test");
        //}
        // ModelAndView model = new ModelAndView();

        //  model.addObject("message"," ");
        if (customer.getName().isEmpty() || customer.getSurname().isEmpty() || customer.getDate() == null) {
            ModelAndView model1 = new ModelAndView("newCustomer1");
            model1.setViewName("newCustomer1");
            model1.addObject("customer", customer);
            model1.addObject("message","All the fields must be fulfilled!");

            return model1 ;
        }
        else {

            customerService.add(customer);
            ModelAndView model1 =  new ModelAndView("redirect:/mvc/customers");
            //model.setViewName("redirect:/mvc/customers");
            return model1;
        }
    }

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
    @GetMapping("/updateCustomer/{id}")
    public String updateCustomer (@PathVariable Long id,Model model1)
    {
        Customer customer=customerService.find(id);
        // customerService.add(customer);

        model1.addAttribute("customer",customer);

        return "updateCustomer1";

    }
    @PostMapping("/update")
    public ModelAndView saveUpdate (Customer customer)
    {
        if (customer.getName().isEmpty() || customer.getSurname().isEmpty() || customer.getDate() == null) {
            ModelAndView model2 = new ModelAndView("updateCustomer1");
            model2.setViewName("updateCustomer1");
            model2.addObject("customer", customer);
            model2.addObject("message","All the fields must be fulfilled!");

            return model2 ;
        }
        else {
            customerService.update(customer);
            ModelAndView model2 =  new ModelAndView("redirect:/mvc/customers");
            return model2;
        }

    }

    @GetMapping ("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Long id, Model model3)
    {
        Customer customer=customerService.find(id);
        model3.addAttribute("customer",customer);
        return "deleteCustomer1";
    }
    @PostMapping("/delete")
    public String deleteUpdate (@ModelAttribute ("customer") Customer customer)
    {

        customerService.delete(customer.getId());

        return "redirect:/mvc/customers";
    }
}
