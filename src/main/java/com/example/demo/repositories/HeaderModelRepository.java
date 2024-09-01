package com.example.demo.repositories;

import com.example.demo.entities.HeaderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderModelRepository extends JpaRepository<HeaderModel, Long> {
}
