package com.reactivemicroservices.productservice.util;

import com.reactivemicroservices.productservice.dto.ProductDto;
import com.reactivemicroservices.productservice.entity.Product;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {
    public static ProductDto toDto(Product product){
        ProductDto dto=new ProductDto();
//        dto.setId(product.getId());
//        dto.setDescription(product.getDescription());
//        dto.setPrice(product.getPrice());
//        using spring way to do this
        BeanUtils.copyProperties(product,dto);
        return dto;
    }
    public static Product toEntity(ProductDto productDto){
        Product product=new Product();
        BeanUtils.copyProperties(productDto,product);
        return product;
    }
}
