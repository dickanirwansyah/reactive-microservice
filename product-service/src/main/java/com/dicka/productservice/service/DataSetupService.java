/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.productservice.service;

import com.dicka.productservice.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author dickanirwansyah
 */

@Slf4j
@Service
public class DataSetupService implements CommandLineRunner{

    @Autowired
    private ProductService productService;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("started insert data setup..");
        ProductDTO product1 = new ProductDTO();
        product1.setDescription("TV LCD");
        product1.setPrice(8700);
        
        ProductDTO product2 = new ProductDTO();
        product2.setDescription("TV NON LCD");
        product2.setPrice(8900);
        
        ProductDTO product3 = new ProductDTO();
        product3.setDescription("TV TABUNG");
        product3.setPrice(7800);
        
        Flux.just(product1, product2, product3)
                .flatMap(p -> this.productService.insertProduct(Mono.just(p)))
                .subscribe(System.out::println);
    }
    
}
