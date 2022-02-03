package com.mindhub.homebanking.repository;

import com.mindhub.homebanking.models.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TurnRepository extends JpaRepository <Turn, Long> {
    Turn findByName(String name);
}
