package com.example.food_project.services.impl;

import com.example.food_project.entity.CategoryEntity;
import com.example.food_project.repository.CategoryRepository;
import com.example.food_project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getExplorerCategory() {
        return categoryRepository.getExplorer();
    }
}
