/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.productservice.repository;

import com.dicka.productservice.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 *
 * @author dickanirwansyah
 */
@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String>{
    
    Flux<Product> findByPriceBetween(Range<Integer>range);
    
}
