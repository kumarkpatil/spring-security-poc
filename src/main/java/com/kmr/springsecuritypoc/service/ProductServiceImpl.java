package com.kmr.springsecuritypoc.service;

import com.kmr.springsecuritypoc.dto.Product;
import com.kmr.springsecuritypoc.entity.UserInfo;
import com.kmr.springsecuritypoc.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserInfoRepository userInfoRepository;
    List<Product> productList = null;

    @PostConstruct
    public void loadProductFromDB(){
        productList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Product.builder()
                        .productId(i)
                        .name("Product"+i)
                        .qty(new Random().nextInt(10))
                        .price(new Random().nextInt(1000))
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public Product getProductById(Integer id) {
        return productList.stream().filter(product -> product.getProductId()==id).findAny()
                .orElseThrow(() -> new RuntimeException("Product "+id+" not found"));
    }


    public String saveUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "User "+userInfo.getName()+" added to Systummmm";

    }
}
