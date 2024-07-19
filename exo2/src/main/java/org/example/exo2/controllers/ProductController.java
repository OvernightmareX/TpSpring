package org.example.exo2.controllers;

import org.example.exo2.entities.Product;
import org.example.exo2.services.ProductService;
import org.example.exo2.utils.ProductCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        List<String> enumNames = Stream.of(ProductCategory.values())
                .map(ProductCategory::name)
                .toList();

        model.addAttribute("productCategories", enumNames);
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
        model.addAttribute("product", monService.getProductByUUID(uuid));
        return "detail";
    }

    @GetMapping("/filter")
    public String getDetailPageByCategoryAndPrice(Model model,
                                                  @RequestParam(value = "category", required = false) String category,
                                                  @RequestParam(value = "price", required = false) double price) {
        ProductCategory productCategory = category != null ? ProductCategory.valueOf(category.toUpperCase()) : null;
        model.addAttribute("allProduct", monService.getProductByCategoryAndMaxPrice(productCategory, price));
        return "list";
    }

    @PostMapping("/filter")
    public String submitDetailPageByCategoryAndPrice(@ModelAttribute Product product, Model model){
        model.addAttribute("category", product.getCategory());
        model.addAttribute("price", product.getPrice());
        model.addAttribute("allProduct", monService.getProductByCategoryAndMaxPrice(product.getCategory(), product.getPrice()));
        model.addAttribute("isFilter", true);
        return "list";
    }

}
