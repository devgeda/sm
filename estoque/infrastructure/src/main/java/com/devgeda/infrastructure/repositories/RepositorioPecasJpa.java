package com.devgeda.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devgeda.domain.models.Peca;

@Repository
public interface RepositorioPecasJpa extends JpaRepository<Peca, Integer> {
	@Query("SELECT p FROM Peca p WHERE p.id = :id")
	Optional<Peca> findById(@Param("id") Integer id);
}