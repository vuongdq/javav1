package com.vdq.controllers;


import com.vdq.models.Product;
import com.vdq.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/v1")
public class ProductController2 {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/product")
    public String getAll(Model model){
        model.addAttribute("listProducts",productRepository.findAll());
        return "admin/product";
    }


    @GetMapping("/product/showNewProductForm")
    public String showNewProductForm(Model model) {
        // create model attribute to bind form data
        Product product = new Product();
        model.addAttribute("product", product);
        return "admin/new_product";
    }

    @PostMapping("/product/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {
        // save employee to database
        productRepository.save(product);
        return "redirect:/admin/v1/product";
    }



    @GetMapping("/product/update/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            model.addAttribute("product", product);
        }
        return "admin/update_product";
    }



    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") long id) {

        // call delete employee method
        this.productRepository.deleteById(id);
        return "redirect:/admin/v1/product";
    }

}
