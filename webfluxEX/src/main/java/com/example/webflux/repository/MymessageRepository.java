package com.example.webflux.repository;

import com.example.webflux.domain.Mymessage;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MymessageRepository extends ReactiveCrudRepository<Mymessage, Long> {
}
