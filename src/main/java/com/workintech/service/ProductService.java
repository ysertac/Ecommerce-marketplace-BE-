package com.workintech.service;

import com.workintech.entity.Product;
import com.workintech.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(long id);

    Product save(Product product);

}
