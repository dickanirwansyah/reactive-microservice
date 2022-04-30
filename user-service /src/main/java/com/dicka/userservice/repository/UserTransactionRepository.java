/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.userservice.repository;

import com.dicka.userservice.entity.UserTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 *
 * @author dickanirwansyah
 */

@Repository
public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction, Integer>{
       
    Flux<UserTransaction> findByUserId(int userId);
}
