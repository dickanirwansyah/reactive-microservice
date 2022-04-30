/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.userservice.util;

import com.dicka.userservice.dto.TransactionRequestDTO;
import com.dicka.userservice.dto.TransactionResponseDTO;
import com.dicka.userservice.dto.TransactionStatus;
import com.dicka.userservice.dto.UserDTO;
import com.dicka.userservice.entity.User;
import com.dicka.userservice.entity.UserTransaction;
import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author dickanirwansyah
 */
public class EntityDTOUtil {
    
    public static UserDTO toDto(User user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
    
    public static User toEntity(UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }
    
    public static UserTransaction toEntity(TransactionRequestDTO requestDTO){
        UserTransaction transaction = new UserTransaction();
        transaction.setId(requestDTO.getUserId());
        transaction.setAmount(requestDTO.getAmount());
        transaction.setTransactionDate(LocalDateTime.now());
        return transaction;
    }
    
    public static TransactionResponseDTO toDto(TransactionRequestDTO transactionRequestDTO,
            TransactionStatus transactionStatus){
        
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setUserId(transactionRequestDTO.getUserId());
        transactionResponseDTO.setAmount(transactionRequestDTO.getAmount());
        transactionResponseDTO.setStatus(transactionStatus);
        return transactionResponseDTO;
    }
}
