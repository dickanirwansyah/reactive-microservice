/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 *
 * @author dickanirwansyah
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
 
    @Id
    private String id;
    private String description;
    private Integer price;
    
}
