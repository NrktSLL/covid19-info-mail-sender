package com.nrkt.covid19infomailsender.repository;

import com.nrkt.covid19infomailsender.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<Person, UUID> {
    Optional<Person> findByEmail(String email);
}
