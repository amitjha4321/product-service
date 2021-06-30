package com.reactivemicroservices.productservice.repository;

import com.reactivemicroservices.productservice.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
    //this will look for > min && < max---exclusive
    //what we really want is >= min && <= max ---inclusive
    //this can be done by @Query also ---custom but here we will use range form spring data domain
    //Flux<Product> findByPriceBetween(int min, int max);

    Flux<Product> findByPriceBetween(Range<Integer> range);
}
