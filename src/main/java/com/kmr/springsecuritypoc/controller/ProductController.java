package com.kmr.springsecuritypoc.controller;

import com.kmr.springsecuritypoc.dto.Product;
import com.kmr.springsecuritypoc.entity.UserInfo;
import com.kmr.springsecuritypoc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Spring security demo unsecured";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllProduct(){
       return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable Integer id){
        return productService.getProductById(id);

    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo){
      return productService.saveUser(userInfo);
    }

}
