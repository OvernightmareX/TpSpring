package org.example.exo2.services;

import org.example.exo2.entities.Product;
import org.example.exo2.utils.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final List<Product> productList;

    public ProductService() {
        productList = new ArrayList<Product>();

        productList.add(Product.builder().id(UUID.randomUUID()).name("Twix").category(ProductCategory.FOOD).price(1.85).build());
        productList.add(Product.builder().id(UUID.randomUUID()).name("Mouse").category(ProductCategory.ELECTRONIC).price(5.50).build());
        productList.add(Product.builder().id(UUID.randomUUID()).name("Cap").category(ProductCategory.FASHION).price(8.99).build());
    }

    public List<Product> getAllProduct(){
        return productList;
    }

    public Product getProductByUUID(UUID uuid) {
        return productList.stream().filter(product -> product.getId().equals(uuid)).findFirst().orElse(null);
    }

    public List<Product> getProductByCategoryAndMaxPrice(ProductCategory category, Double price) {
        List<Product> filteredProductList = productList;
        if(category != null)
            filteredProductList = filteredProductList.stream().filter(product -> product.getCategory().equals(category)).toList();

        if(price != 0)
            filteredProductList = filteredProductList.stream().filter(product -> product.getPrice() <= price).toList();

        return filteredProductList;
    }

}
