package com.inmotion.trafico.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficRequestDTO {
    
    // ENCABEZADO
    private String coachName;
    private String greeters;
    private String adv;
    
    // DATOS DEL CLIENTE
    @NotBlank(message = "El nombre es requerido")
    private String firstName;
    
    @NotBlank(message = "El apellido es requerido")
    private String lastName;
    
    @NotBlank(message = "El número de teléfono es requerido")
    private String phone;
    
    @NotBlank(message = "El correo electrónico es requerido")
    @Email(message = "Debe ser un correo válido")
    private String email;
    
    private String requiredModel;
    private String appointment;
    private String internetOther;
    private String prospection;
    
    // AÑADIDO: firstVisit
    private String firstVisit;
    
    // ACTIVIDADES
    private String demoEstatica;
    private String pruebaManejo;
    private String hojaOpciones;
    private String planFinanciamiento;
    private String toyotour;
    private String autoActualAvaluo;
    private String intervinoCoach;
    
    // SEGUIMIENTO
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String comments;
    private LocalDateTime nextVisit;
}