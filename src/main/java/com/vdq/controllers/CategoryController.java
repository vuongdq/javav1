package com.vdq.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("categories")
public class CategoryController {
    // http://vuongdq:8080/categories
    @GetMapping("")
    public String getAllCategories(ModelMap modelMap){
//        String name = modelMap.addAttribute("name": "Vuong");
        modelMap.addAttribute("name","Vuong");
        modelMap.addAttribute("age",18);
        return "category";
    }

}
