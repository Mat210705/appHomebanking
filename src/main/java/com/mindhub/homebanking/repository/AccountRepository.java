package com.mindhub.homebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.mindhub.homebanking.models.Account;
@RepositoryRestResource
public interface AccountRepository extends JpaRepository <Account, Long> {
    Account findByNumber(String number);
}