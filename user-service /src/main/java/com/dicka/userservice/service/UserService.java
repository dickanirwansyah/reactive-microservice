/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.userservice.service;

import com.dicka.userservice.dto.UserDTO;
import com.dicka.userservice.repository.UserRepository;
import com.dicka.userservice.util.EntityDTOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author dickanirwansyah
 */

@Slf4j
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public Flux<UserDTO> all(){
        log.info("flux get all users");
        return this.userRepository.findAll()
                .map(EntityDTOUtil::toDto);
    }
    
    public Mono<UserDTO> getUserId(Integer id){
        log.info("mono get user by id -> {} ",id);
        return this.userRepository.findById(id)
                .map(user -> UserDTO.builder()
                        .id(user.getId())
                        .balance(user.getBalance())
                        .name(user.getName())
                        .build());
    }
    
    public Mono<UserDTO> createUser(Mono<UserDTO> userDto){
        log.info("mono request create user -> {} ",userDto.toString());
        return userDto
                .map(EntityDTOUtil::toEntity)
                .flatMap(this.userRepository::save)
                .map(EntityDTOUtil::toDto);
    }
    
    
    public Mono<UserDTO> updateUser(Integer id, Mono<UserDTO> userDto){
        log.info("mono request update user -> {} ",userDto.toString());
        return this.userRepository.findById(id)
                .flatMap(user -> userDto.map(EntityDTOUtil::toEntity)
                        .doOnNext(userid -> userid.setId(id)))
                .flatMap(this.userRepository::save)
                .map(EntityDTOUtil::toDto);
    }
    
    public Mono<Void> deleteUser(Integer id){
        log.info("delete user by id -> {} ",id);
        return this.userRepository.deleteById(id);
    }
}
