package com.inmotion.trafico.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "traffic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Traffic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "cliente_nombre")
    private String clienteNombre;
    
    @Column(name = "cliente_telefono")
    private String clienteTelefono;
    
    @Column(name = "cliente_email")
    private String clienteEmail;
    
    @Column(name = "coach_name")
    private String coachName;
    
    private String greeters;
    private String adv;
    
    @Column(name = "entry_time")
    private LocalDateTime entryTime;
    
    @Column(name = "exit_time")
    private LocalDateTime exitTime;
    
    @Column(name = "first_visit")
    private Boolean firstVisit;
    
    @Column(name = "visit_number")
    private Integer visitNumber;
    
    private Boolean appointment;
    
    @Column(name = "internet_other")
    private String internetOther;
    
    private String prospection;
    
    @Column(name = "required_model")
    private String requiredModel;
    
    @Column(name = "demo_estatica")
    private Boolean demoEstatica;
    
    @Column(name = "prueba_manejo")
    private Boolean pruebaManejo;
    
    @Column(name = "hoja_opciones")
    private Boolean hojaOpciones;
    
    @Column(name = "plan_financiamiento")
    private Boolean planFinanciamiento;
    
    private Boolean toyotour;
    
    @Column(name = "auto_actual_avaluo")
    private Boolean autoActualAvaluo;
    
    @Column(name = "intervino_coach")
    private Boolean intervinoCoach;
    
    private String comments;
    
    @Column(name = "next_visit")
    private LocalDateTime nextVisit;
}