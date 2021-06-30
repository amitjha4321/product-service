package com.reactivemicroservices.productservice.service;

import com.reactivemicroservices.productservice.dto.ProductDto;
import com.reactivemicroservices.productservice.repository.ProductRepository;
import com.reactivemicroservices.productservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Flux<ProductDto> getAll(){
       return this.repository.findAll()
               .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> getProductById(String id){
        return this.repository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> insertProduct(Mono<ProductDto> productDtoMono){
        return productDtoMono
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.repository::insert)
                .map(EntityDtoUtil::toDto);
        //we are getting a productdto (from method argument), we are converting it to entity ,we then insert it in to db using reactive repository
        //we have used flatmap to avoid mono inside mono
        //when we insert this middle line will give us backthe mono of updated entity with id so we have used flat map to extract that
        //and then we again converted it back to dto and returned it
    }
}
