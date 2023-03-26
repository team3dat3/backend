package com.team3dat3.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team3dat3.backend.entity.Authenticatable;

public interface AuthenticatableRepository extends JpaRepository<Authenticatable, String> {
}
