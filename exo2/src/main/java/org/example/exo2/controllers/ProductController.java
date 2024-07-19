package org.example.exo2.controllers;

import org.example.exo2.services.ProductService;
import org.example.exo2.utils.ProductCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService monService;

    public ProductController(final ProductService monService) {
        this.monService = monService;
    }

    @GetMapping
    public String homePage(){
        return "home";
    }

    @GetMapping("/list")
    public String listPage(Model model){
        model.addAttribute("allProduct", monService.getAllProduct());
        return "list";
    }

    @GetMapping("/detail/{uuid}")
    public String detailPage(Model model, @PathVariable("uuid") UUID uuid){
        model.addAttribute("entity", monService.getProductByUUID(uuid));
        return "detail";
    }

    @GetMapping("/filter")
    public String detailPageByName(Model model, @RequestParam(value = "category", required = false) String category, @RequestParam(value = "price", required = false) Optional<Double> price){
        ProductCategory productCategory = null;
        double productPrice = 0;

        if(category != null)
           productCategory = ProductCategory.valueOf(category.toUpperCase());

        if(price.isPresent())
            productPrice = price.get();

        model.addAttribute("filteredList", monService.getProductByCategoryAndMaxPrice(productCategory, productPrice));
        return "filterPage";
    }

}
