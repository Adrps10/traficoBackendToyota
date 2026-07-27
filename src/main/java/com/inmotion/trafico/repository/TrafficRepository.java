package com.inmotion.trafico.repository;

import com.inmotion.trafico.model.Traffic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrafficRepository extends JpaRepository<Traffic, Long> {
    
    // Usar parámetros posicionales con ?1, ?2, ?3
    @Query(value = "SELECT * FROM traffic WHERE DATE(entry_time) = CURRENT_DATE ORDER BY entry_time DESC LIMIT ?1 OFFSET ?2", 
           nativeQuery = true)
    List<Traffic> findTodayVisits(int limit, int offset);
    
    // Consulta para contar las visitas de hoy
    @Query(value = "SELECT COUNT(*) FROM traffic WHERE DATE(entry_time) = CURRENT_DATE", 
           nativeQuery = true)
    long countTodayVisits();
    
    @Query("SELECT t FROM Traffic t WHERE " +
           "LOWER(t.clienteNombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "t.clienteTelefono LIKE CONCAT('%', :search, '%') OR " +
           "LOWER(t.clienteEmail) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "ORDER BY t.entryTime DESC")
    Page<Traffic> searchAll(@Param("search") String search, Pageable pageable);
    
    List<Traffic> findByClienteTelefono(String telefono);
}