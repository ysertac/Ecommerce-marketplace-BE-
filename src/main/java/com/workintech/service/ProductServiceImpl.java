package com.workintech.service;

import com.workintech.entity.Product;
import com.workintech.exception.ProductException;
import com.workintech.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        throw new ProductException("Product not found with that id: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product delete(long id) {
        Optional<Product> productFound = productRepository.findById(id);
        if (productFound.isPresent()) {
            productRepository.delete(productFound.get());
            return productFound.get();
        }
        throw new ProductException("Product not found with that id: " + id, HttpStatus.NOT_FOUND);
    }
}
