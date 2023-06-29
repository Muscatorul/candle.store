package com.cande.store.repository;

import com.cande.store.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolleRepository extends JpaRepository<Role,Long> {

    Optional<Role>findByName(String name);
}
