package com.inmotion.trafico.repository;

import com.inmotion.trafico.model.Visita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Long> {
    
    // ============================================================
    // CONSULTAS CON NATIVE SQL
    // ============================================================
    
    @Query(value = "SELECT v.* FROM visitas v WHERE v.cliente_id = :clienteId ORDER BY v.entry_time DESC", 
           nativeQuery = true)
    List<Visita> findByClienteIdOrderByEntryTimeDesc(@Param("clienteId") String clienteId);
    
    // CONSULTA CON JOIN PARA OBTENER DATOS DEL CLIENTE
    @Query(value = "SELECT v.id, v.cliente_id, c.cliente as cliente_nombre, c.telefono, c.email, " +
                   "v.coach_name, v.greeters, v.adv, v.entry_time, v.exit_time, v.first_visit, " +
                   "v.visit_number, v.appointment, v.internet_other, v.prospection, v.required_model, " +
                   "v.demo_estatica, v.prueba_manejo, v.hoja_opciones, v.plan_financiamiento, " +
                   "v.toyotour, v.auto_actual_avaluo, v.intervino_coach, v.comments, v.next_visit, v.timestamp " +
                   "FROM visitas v " +
                   "JOIN clientes c ON v.cliente_id = c.id " +
                   "WHERE DATE(v.entry_time) = CURRENT_DATE " +
                   "ORDER BY v.entry_time DESC", 
           nativeQuery = true)
    Page<Object[]> findTodayVisitsWithClient(Pageable pageable);
    
    @Query(value = "SELECT v.id, v.cliente_id, c.cliente as cliente_nombre, c.telefono, c.email, " +
                   "v.coach_name, v.greeters, v.adv, v.entry_time, v.exit_time, v.first_visit, " +
                   "v.visit_number, v.appointment, v.internet_other, v.prospection, v.required_model, " +
                   "v.demo_estatica, v.prueba_manejo, v.hoja_opciones, v.plan_financiamiento, " +
                   "v.toyotour, v.auto_actual_avaluo, v.intervino_coach, v.comments, v.next_visit, v.timestamp " +
                   "FROM visitas v " +
                   "JOIN clientes c ON v.cliente_id = c.id " +
                   "WHERE LOWER(c.cliente) LIKE LOWER(CONCAT('%', :search, '%')) " +
                   "ORDER BY v.entry_time DESC", 
           nativeQuery = true)
    Page<Object[]> searchByClientNameWithClient(@Param("search") String search, Pageable pageable);
    
    @Query(value = "SELECT COUNT(*) FROM visitas WHERE DATE(entry_time) = CURRENT_DATE", 
           nativeQuery = true)
    Long countTodayVisits();
    
    // ============================================================
    // CONSULTAS SIN PAGINACIÓN (para compatibilidad)
    // ============================================================
    
    @Query(value = "SELECT v.* FROM visitas v WHERE v.cliente_id = :clienteId ORDER BY v.entry_time DESC", 
           nativeQuery = true)
    List<Visita> findVisitsByClienteId(@Param("clienteId") String clienteId);
    
    @Query(value = "SELECT v.* FROM visitas v JOIN clientes c ON v.cliente_id = c.id " +
                   "WHERE LOWER(c.cliente) LIKE LOWER(CONCAT('%', :search, '%')) " +
                   "ORDER BY v.entry_time DESC", 
           nativeQuery = true)
    Page<Visita> searchByClientName(@Param("search") String search, Pageable pageable);
}