package com.inmotion.trafico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "visitas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Visita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "cliente_id", nullable = false, length = 20)
    private String clienteId;  // ← SOLO EL ID, NO LA RELACIÓN
    
    @Column(name = "coach_name", length = 100)
    private String coachName;
    
    @Column(name = "greeters", length = 100)
    private String greeters;
    
    @Column(name = "adv", length = 100)
    private String adv;
    
    @Column(name = "entry_time")
    private LocalDateTime entryTime;
    
    @Column(name = "exit_time")
    private LocalDateTime exitTime;
    
    @Column(name = "first_visit", length = 2)
    private String firstVisit;
    
    @Column(name = "visit_number")
    private Integer visitNumber = 1;
    
    @Column(name = "appointment", length = 2)
    private String appointment;
    
    @Column(name = "internet_other", length = 100)
    private String internetOther;
    
    @Column(name = "prospection", length = 2)
    private String prospection;
    
    @Column(name = "required_model", length = 100)
    private String requiredModel;
    
    @Column(name = "demo_estatica", length = 50)
    private String demoEstatica;
    
    @Column(name = "prueba_manejo", length = 50)
    private String pruebaManejo;
    
    @Column(name = "hoja_opciones", length = 50)
    private String hojaOpciones;
    
    @Column(name = "plan_financiamiento", length = 50)
    private String planFinanciamiento;
    
    @Column(name = "toyotour", length = 50)
    private String toyotour;
    
    @Column(name = "auto_actual_avaluo", length = 2)
    private String autoActualAvaluo;
    
    @Column(name = "intervino_coach", length = 2)
    private String intervinoCoach;
    
    @Column(columnDefinition = "TEXT")
    private String comments;
    
    @Column(name = "next_visit")
    private LocalDateTime nextVisit;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}