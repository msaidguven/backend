package com.project.soruCevap.controllers;

import com.project.soruCevap.entities.Category;
import com.project.soruCevap.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategory (@RequestParam Optional<String> parentId){
        return categoryService.getAllCategory(parentId);
    }

    @GetMapping("/{categoryId}") // -> /category/
    public Category getOneCategoryById(@PathVariable String categoryId){
        return categoryService.getOneCategoryById(categoryId);
    }

    @PostMapping("/createNewCategory") // -> /category/createNewCategory
    public Category createOneCategory(@RequestBody Category newCategory){
        return categoryService.createOneCategory(newCategory);
    }

    @PutMapping("/updateCategory/{categoryId}") // -> /category/updateCategory/
    public Category updateOneCategory(@PathVariable String categoryId, @RequestBody Category updateCategory){
        return categoryService.updateOneCategory(categoryId, updateCategory);
    }

    @DeleteMapping("/deleteCategory/{categoryId}")
    public Boolean deleteCategoryById(@PathVariable String categoryId){
        return categoryService.deleteCategoryById(categoryId);
    }
}
