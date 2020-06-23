package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.Province;
import com.example.demo.service.customer.ICustomerService;
import com.example.demo.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    public ICustomerService customerService;
    @Autowired
    public IProvinceService provinceService;
    @Autowired
    MessageSource messageSource;

    @ModelAttribute("provinces")
    public Page<Province> provinces(Pageable pageable) {
        return provinceService.findAll(pageable);
    }

    @GetMapping("")
    public ModelAndView showAllList(@PageableDefault(value = 6) Pageable pageable, @RequestParam("s") Optional<String> s) {
        Page<Customer> list;
        if (s.isPresent()) {
            list = customerService.findAllByFirstNameContaining(s.get(), pageable);
        } else {
            list = customerService.findAll(pageable);
        }
        return new ModelAndView("customer/customerList", "list", list);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("customer/showEditForm");
        modelAndView.addObject("customer", customer.get());
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/customer";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        customerService.delete(id);
        return "redirect:/customer";
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        return new ModelAndView("customer/createForm", "customer", new Customer());
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Customer customer, BindingResult rs) {
        System.out.println(rs);
        customerService.save(customer);
        return "redirect:/";
    }
}
