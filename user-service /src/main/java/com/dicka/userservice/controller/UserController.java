/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.userservice.controller;

import com.dicka.userservice.dto.UserDTO;
import com.dicka.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping(value = "/all")
    public Flux<UserDTO> all(){
        return userService.all();
    }
    
    @GetMapping(value = "/get/{id}")
    public Mono<ResponseEntity<UserDTO>> getUserId(@PathVariable("id")Integer id){
      return this.userService.getUserId(id)
              .map(ResponseEntity::ok)
              .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping(value = "/update/{id}")
    public Mono<ResponseEntity<UserDTO>> update(@PathVariable("id")Integer id,
            @RequestBody Mono<UserDTO> userDto){
        return this.userService.updateUser(id, userDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping(value = "/save")
    public Mono<UserDTO> save(@RequestBody Mono<UserDTO> userDto){
        return this.userService.createUser(userDto);
    }
    
    @GetMapping(value = "/delete/{id}")
    public Mono<Void> delete(@PathVariable("id")Integer id){
        return this.userService.deleteUser(id);
    }
}
