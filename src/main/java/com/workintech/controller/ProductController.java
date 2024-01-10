package com.workintech.controller;

import com.workintech.dto.ProductResponse;
import com.workintech.entity.Category;
import com.workintech.entity.Product;
import com.workintech.service.CategoryService;
import com.workintech.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        List<Product> allProducts = productService.findAll();
        List<ProductResponse> products = new ArrayList<>();
        for (Product p : allProducts) {
            products.add(new ProductResponse(p.getName(), p.getDescription(), p.getRating(), p.getPrice(),
                    p.getStock(), p.getImage()));
        }
        return products;
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable long id) {
        Product productFound = productService.findById(id);
        return new ProductResponse(productFound.getName(), productFound.getDescription(), productFound.getRating(),
                productFound.getPrice(), productFound.getStock(), productFound.getImage());
    }

    @PostMapping
    public ProductResponse createProduct(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        return new ProductResponse(savedProduct.getName(), savedProduct.getDescription(), savedProduct.getRating(),
                savedProduct.getPrice(), savedProduct.getStock(), savedProduct.getImage());
    }

    @PostMapping("/{id}")
    public ProductResponse createProduct(@PathVariable long id, @RequestBody Product product) {
        Category category = categoryService.findById(id);
        product.setCategory(category);
        category.addProduct(product);
        Product savedProduct = productService.save(product);
        return new ProductResponse(savedProduct.getName(), savedProduct.getDescription(), savedProduct.getRating(),
                savedProduct.getPrice(), savedProduct.getStock(), savedProduct.getImage());
    }

    @DeleteMapping("/{id}")
    public ProductResponse deleteProduct(@PathVariable long id) {
        Product deletedProduct = productService.delete(id);
        return new ProductResponse(deletedProduct.getName(), deletedProduct.getDescription(), deletedProduct.getRating(),
                deletedProduct.getPrice(), deletedProduct.getStock(), deletedProduct.getImage());
    }

}
