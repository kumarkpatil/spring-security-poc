package com.kmr.springsecuritypoc.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

    Integer productId;
    String name;
    Integer qty;
    Integer price;

}
