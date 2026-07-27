package com.inmotion.trafico.repository;

import com.inmotion.trafico.model.ToyotaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToyotaModelRepository extends JpaRepository<ToyotaModel, Long> {
    List<ToyotaModel> findByActiveTrueOrderByName();
}