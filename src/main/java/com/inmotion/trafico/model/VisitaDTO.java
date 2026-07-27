package com.inmotion.trafico.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitaDTO {
    private Long id;
    private String clienteId;
    private String clienteNombre;
    private String coachName;
    private String greeters;
    private String adv;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String firstVisit;
    private Integer visitNumber;
    private String appointment;
    private String internetOther;
    private String prospection;
    private String requiredModel;
    private String demoEstatica;
    private String pruebaManejo;
    private String hojaOpciones;
    private String planFinanciamiento;
    private String toyotour;
    private String autoActualAvaluo;
    private String intervinoCoach;
    private String comments;
    private LocalDateTime nextVisit;
    private LocalDateTime timestamp;
}