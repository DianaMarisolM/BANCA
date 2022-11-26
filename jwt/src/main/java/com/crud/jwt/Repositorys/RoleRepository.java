package com.crud.jwt.Repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.jwt.Entitys.ERole;
import com.crud.jwt.Entitys.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    public Optional<Role> findByName(ERole name);
}
