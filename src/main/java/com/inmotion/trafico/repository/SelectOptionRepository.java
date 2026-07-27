package com.inmotion.trafico.repository;

import com.inmotion.trafico.model.SelectOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectOptionRepository extends JpaRepository<SelectOption, Long> {
    List<SelectOption> findByCategoryAndActiveTrueOrderByValue(String category);
}