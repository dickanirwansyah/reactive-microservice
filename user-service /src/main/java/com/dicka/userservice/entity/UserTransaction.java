/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.userservice.entity;

import java.time.LocalDateTime;
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
public class UserTransaction {
    
    @Id
    private Integer id;
    private Integer userId;
    private Integer amount;
    private LocalDateTime transactionDate;
    
}
