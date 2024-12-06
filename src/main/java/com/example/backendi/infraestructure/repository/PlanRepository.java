package com.example.backendi.infraestructure.repository;
import com.example.backendi.domain.model.PlanEstudios;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

public interface PlanRepository extends ReactiveCrudRepository<PlanEstudios, Long>{

    Mono<PlanEstudios> findByCodigo(String codigo);
}
