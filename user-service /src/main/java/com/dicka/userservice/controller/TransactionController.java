/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.userservice.controller;

import com.dicka.userservice.dto.TransactionRequestDTO;
import com.dicka.userservice.dto.TransactionResponseDTO;
import com.dicka.userservice.entity.UserTransaction;
import com.dicka.userservice.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author dickanirwansyah
 */

@Slf4j
@RestController
@RequestMapping(value = "/user/transaction")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;
    
    @GetMapping(value = "/all")
    public Flux<TransactionResponseDTO> all(){
        return this.transactionService.allTransaction();
    }
    
    @GetMapping(value = "/byuser/{id}")
    public Flux<UserTransaction> byUser(@PathVariable("id")int id){
        return this.transactionService.getByUserId(id);
    }
    
    @PostMapping(value = "/create")
    public Mono<TransactionResponseDTO> create(@RequestBody Mono<TransactionRequestDTO> requestTransaction){
        return requestTransaction.flatMap(this.transactionService::createTransaction);
    }
}
