/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.productservice.util;

import com.dicka.productservice.dto.ProductDTO;
import com.dicka.productservice.entity.Product;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author dickanirwansyah
 */
public class EntityDTOUtil {
    
    public static ProductDTO toDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }
    
    public static Product toEntity(ProductDTO productDTO){
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }
}
