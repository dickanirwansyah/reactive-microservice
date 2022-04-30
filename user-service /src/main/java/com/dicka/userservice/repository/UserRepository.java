/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.userservice.repository;

import com.dicka.userservice.entity.User;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 *
 * @author dickanirwansyah
 */
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Integer>{
    
    @Modifying
    @Query("update user set balance = balance - :amount "
            + "where id = :userId "
            + "and balance >= :amount")
    Mono<Boolean> updateUserBalance(int userId, int amount);
    
}
