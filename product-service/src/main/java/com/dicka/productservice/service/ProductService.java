/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.productservice.service;

import com.dicka.productservice.dto.ProductDTO;
import com.dicka.productservice.repository.ProductRepository;
import com.dicka.productservice.util.EntityDTOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author dickanirwansyah
 */

@Service
@Slf4j
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public Flux<ProductDTO> getAll(){
        log.info("flux get all product");
        return this.productRepository.findAll()
                .map(EntityDTOUtil::toDTO);
    }
    
    public Flux<ProductDTO> getProductByRange(int min, int max){
        log.info("flux get product range from {} to {}",min,max);
        return this.productRepository.findByPriceBetween(Range.open(min, max))
                .map(EntityDTOUtil::toDTO);
    }
    
    public Mono<ProductDTO> getProductById(String id){
        log.info("mono get product id -> {} ",id);
        return this.productRepository.findById(id)
                .map(EntityDTOUtil::toDTO);
    }
    
    public Mono<ProductDTO> insertProduct(Mono<ProductDTO> productDtoMono){
       log.info("insert product : {} ",productDtoMono.toString());
       return productDtoMono
                .map(EntityDTOUtil::toEntity)
                .flatMap(this.productRepository::insert)
                .map(EntityDTOUtil::toDTO);
    }
    
    public Mono<ProductDTO> updateProduct(String id, Mono<ProductDTO> productDtoMono){
        log.info("update product : {} ",productDtoMono.toString());
        return this.productRepository.findById(id)
                .flatMap(p -> productDtoMono.map(EntityDTOUtil::toEntity)
                        .doOnNext(e -> e.setId(id)))
                         .flatMap(this.productRepository::save)
                          .map(EntityDTOUtil::toDTO);
    }
    
    public Mono<Void> deleteProduct(String id){
        return this.productRepository.deleteById(id);
    }
}
