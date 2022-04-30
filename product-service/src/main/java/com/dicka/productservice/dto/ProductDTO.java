/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author dickanirwansyah
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    
    private String id;
    private String description;
    private Integer price;
    
}
