package com.workintech.service;

import com.workintech.entity.Category;
import com.workintech.exception.CategoryException;
import com.workintech.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        }
        throw new CategoryException("Category not found with that id: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category delete(long id) {
        Optional<Category> categoryFound = categoryRepository.findById(id);
        if (categoryFound.isPresent()) {
            categoryRepository.delete(categoryFound.get());
            return categoryFound.get();
        }
        throw new CategoryException("Category not found with that id: " + id, HttpStatus.NOT_FOUND);
    }
}
