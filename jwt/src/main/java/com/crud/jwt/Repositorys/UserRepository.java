package com.crud.jwt.Repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.jwt.Entitys.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUsername(String username);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);
}
