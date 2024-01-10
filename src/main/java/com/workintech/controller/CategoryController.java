package com.workintech.controller;

import com.workintech.dto.CategoryResponse;
import com.workintech.entity.Category;
import com.workintech.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        List<Category> allCategories = categoryService.findAll();
        List<CategoryResponse> categories = new ArrayList<>();
        for (Category c : allCategories) {
            categories.add(new CategoryResponse(c.getTitle(), c.getImg(), c.getRating(), c.getGender()));
        }
        return categories;
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable long id) {
        Category foundCategory = categoryService.findById(id);
        return new CategoryResponse(foundCategory.getTitle(), foundCategory.getImg(),
                foundCategory.getRating(), foundCategory.getGender());
    }

    @PostMapping
    public CategoryResponse createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.save(category);
        return new CategoryResponse(savedCategory.getTitle(), savedCategory.getImg(),
                savedCategory.getRating(), savedCategory.getGender());
    }

    @DeleteMapping("/{id}")
    public CategoryResponse deleteCategory(@PathVariable long id) {
        Category removedCategory = categoryService.delete(id);
        return new CategoryResponse(removedCategory.getTitle(), removedCategory.getImg(),
                removedCategory.getRating(), removedCategory.getGender());
    }

}
