package com.inmotion.trafico.repository;

import com.inmotion.trafico.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
    List<Coach> findByActiveTrueOrderByName();
}