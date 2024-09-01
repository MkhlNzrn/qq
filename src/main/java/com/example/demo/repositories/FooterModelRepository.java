package com.example.demo.repositories;

import com.example.demo.entities.FooterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooterModelRepository extends JpaRepository<FooterModel, Long> {
}
