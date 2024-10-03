package com.nxc.nexuschain.services;

import com.nxc.nexuschain.entities.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Integer productId);
    List<Product> getAllProducts();
}
