package com.pqrs_backend.repository;

import com.pqrs_backend.entity.FQA;
import com.pqrs_backend.entity.Queja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FQARepository extends JpaRepository<FQA, Integer> {
}
