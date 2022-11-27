package com.grupo66.security.security.Repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo66.security.security.Entitys.ERole;
import com.grupo66.security.security.Entitys.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    public Optional<Role> findByName(ERole name);
}
