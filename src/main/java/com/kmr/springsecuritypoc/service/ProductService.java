package com.kmr.springsecuritypoc.service;

import com.kmr.springsecuritypoc.dto.Product;
import com.kmr.springsecuritypoc.entity.UserInfo;

import java.util.List;

public interface ProductService {


    List<Product> getAllProducts();

    Product getProductById(Integer id);

    String saveUser(UserInfo userInfo);
}
