package com.project.soruCevap.repository;

import com.project.soruCevap.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findAllByParentId(String parentId);
}
