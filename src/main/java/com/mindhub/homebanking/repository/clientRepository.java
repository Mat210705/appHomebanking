package com.mindhub.homebanking.repository;

import com.mindhub.homebanking.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.mindhub.homebanking.models.Client;
@RepositoryRestResource
public interface clientRepository extends JpaRepository <Client, Long> {
    Client findByEmail(String email);
    Client findByCards(Card card);

}
