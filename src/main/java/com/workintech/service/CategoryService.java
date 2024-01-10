package com.workintech.service;

import com.workintech.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(long id);

    Category save(Category category);

    Category delete(long id);

}
