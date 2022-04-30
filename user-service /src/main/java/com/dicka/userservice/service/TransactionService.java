package com.dicka.userservice.service;

import com.dicka.userservice.dto.TransactionRequestDTO;
import com.dicka.userservice.dto.TransactionResponseDTO;
import com.dicka.userservice.dto.TransactionStatus;
import com.dicka.userservice.entity.UserTransaction;
import com.dicka.userservice.repository.UserRepository;
import com.dicka.userservice.repository.UserTransactionRepository;
import com.dicka.userservice.util.EntityDTOUtil;
import java.time.LocalDateTime;
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
public class TransactionService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTransactionRepository userTransactionRepository;
    
    
    public Flux<TransactionResponseDTO> allTransaction(){
        log.info("flux request all transaction");
        return this.userTransactionRepository.findAll()
                .map(data -> TransactionResponseDTO.builder()
                        .amount(data.getAmount())
                        .userId(data.getUserId())
                        .build());
    }
    
    public Flux<UserTransaction> getByUserId(int userId){
        log.info("flux request get by user id -> {} ",userId);
        return this.userTransactionRepository.findByUserId(userId);
    }
    
    public Mono<TransactionResponseDTO> createTransaction(final TransactionRequestDTO requestDTO){
        log.info("mono request create transaction -> {} ",requestDTO.toString());
        return this.userRepository.updateUserBalance(requestDTO.getUserId(), requestDTO.getAmount())
                .filter(Boolean::booleanValue)
                .map(b -> EntityDTOUtil.toEntity(requestDTO))
                .flatMap(data -> this.userTransactionRepository.save(UserTransaction
                        .builder()
                        .userId(requestDTO.getUserId())
                        .amount(requestDTO.getAmount())
                        .transactionDate(LocalDateTime.now())
                        .build()))
                .map(ut -> EntityDTOUtil.toDto(requestDTO, TransactionStatus.APPROVDED))
                .defaultIfEmpty(EntityDTOUtil.toDto(requestDTO, TransactionStatus.DECLINE));
    }
}
