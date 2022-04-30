/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.productservice.controller;

import com.dicka.productservice.dto.ProductDTO;
import com.dicka.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author dickanirwansyah
 */

@Slf4j
@RestController
@RequestMapping(value = "/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping(value = "/all")
    public Flux<ProductDTO> all(){
        return this.productService.getAll();
    }
 
    @GetMapping(value = "/get/{id}")
    public Mono<ResponseEntity<ProductDTO>> getProductId(@PathVariable("id")String id){
        return this.productService.getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound()
                .build());
    }
    
    @GetMapping(value = "/product-range")
    public Flux<ProductDTO> getProductRange(
            @RequestParam("min")int min,
            @RequestParam("max")int max){
        return this.productService.getProductByRange(min, max);
    }
    
    @PostMapping(value = "/update/{id}")
    public Mono<ResponseEntity<ProductDTO>> update(@PathVariable("id")String id, 
            @RequestBody Mono<ProductDTO> producDto){
        return this.productService.updateProduct(id, producDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound()
                .build());
    }
    
    @PostMapping(value = "/save")
    public Mono<ProductDTO> save(@RequestBody Mono<ProductDTO> productDTO){
        return this.productService.insertProduct(productDTO);
    }
    
    
    @GetMapping(value = "/delete/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id")String id){
        return this.productService.deleteProduct(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound()
                .build());
    }
}
