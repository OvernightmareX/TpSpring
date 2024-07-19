package org.example.exo2.controllers;

import org.example.exo2.entities.Product;
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
    public String homePage(Model model){
        model.addAttribute("product", new Product());
        return "home";
    }

    @GetMapping("/list")
    public String listPage(Model model){
        model.addAttribute("allProduct", monService.getAllProduct());
        model.addAttribute("isFilter", false);
        return "list";
    }

    @GetMapping("/detail/{uuid}")
    public String detailPage(Model model, @PathVariable("uuid") UUID uuid){
        model.addAttribute("entity", monService.getProductByUUID(uuid));
        return "detail";
    }

    @GetMapping("/filter")
    public String getDetailPageByName(Model model, @RequestParam(value = "category", required = false) String category, @RequestParam(value = "price", required = false) Optional<Double> price){
        ProductCategory productCategory = null;
        double productPrice = 0;

        if(category != null)
           productCategory = ProductCategory.valueOf(category.toUpperCase());

        if(price.isPresent())
            productPrice = price.get();

        model.addAttribute("allProduct", monService.getProductByCategoryAndMaxPrice(productCategory, productPrice));
        return "list";
    }

    @PostMapping("/filter")
    public String submitDetailPageByName(@ModelAttribute Product product, Model model){
        model.addAttribute("category", product.getCategory());
        model.addAttribute("price", product.getPrice());
        model.addAttribute("allProduct", monService.getProductByCategoryAndMaxPrice(product.getCategory(), product.getPrice()));
        model.addAttribute("isFilter", true);
        return "list";
    }

}
