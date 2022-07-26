package com.vdq.controllers;


import com.vdq.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/api/product")
public class ProductController1 {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public String getAll(Model model){
        model.addAttribute("listProducts",productRepository.findAll());
        return "admin/api/product";
    }
}
