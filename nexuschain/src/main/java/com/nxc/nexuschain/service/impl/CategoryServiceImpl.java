package com.nxc.nexuschain.service.impl;

import com.nxc.nexuschain.pojo.Category;
import com.nxc.nexuschain.repository.CategoryRepository;
import com.nxc.nexuschain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }
}
