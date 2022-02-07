package com.project.soruCevap.services;

import com.project.soruCevap.entities.Category;
import com.project.soruCevap.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    public static String clearTurkishChars(String str) {
        String ret = str.toLowerCase();
        char[] turkishChars = new char[] {0x131, 0x130, 0xFC, 0xDC, 0xF6, 0xD6, 0x15F, 0x15E, 0xE7, 0xC7, 0x11F, 0x11E, ' '};
        char[] englishChars = new char[] {'i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G', '_'};
        for (int i = 0; i < turkishChars.length; i++) {
            ret = ret.replaceAll(new String(new char[]{turkishChars[i]}), new String(new char[]{englishChars[i]}));
        }
        return ret;
    }

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategory(Optional<String> parentId) {
        if (parentId.isPresent()){
            return categoryRepository.findAllByParentId(parentId.get());
        }
        return categoryRepository.findAll();
    }


    public Category getOneCategoryById(String categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public Category createOneCategory(Category newCategory) {
        String seflink = clearTurkishChars(newCategory.getCategoryName());
        newCategory.setCategorySefLink(seflink);
        return categoryRepository.save(newCategory);
    }

    public Category updateOneCategory(String categoryId, Category updateCategory) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null){
            Category toCategory = updateCategory;
            toCategory.setId(updateCategory.getId());
            toCategory.setParentId(updateCategory.getParentId());
            toCategory.setCategoryName(updateCategory.getCategoryName());
            String seflink = clearTurkishChars(updateCategory.getCategoryName());
            toCategory.setCategorySefLink(seflink);
            return categoryRepository.save(toCategory);
        }
        return null;
    }

    public Boolean deleteCategoryById(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null){
            categoryRepository.deleteById(categoryId);
            return true;
        }
        return false;
    }
}
