package com.inmotion.trafico.controller;

import com.inmotion.trafico.model.*;
import com.inmotion.trafico.service.TrafficService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/traffic")
@RequiredArgsConstructor
public class TrafficController {

    private final TrafficService trafficService;

    // ============================================================
    // ENDPOINTS PRINCIPALES
    // ============================================================

    @GetMapping("/records")
    public ResponseEntity<Map<String, Object>> getRecords(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("entryTime").descending());
        Page<Traffic> result = trafficService.getRecords(pageable, search);
        
        Map<String, Object> response = new HashMap<>();
        response.put("content", result.getContent());
        response.put("totalRecords", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());
        response.put("currentPage", result.getNumber());
        response.put("todayCount", trafficService.countToday());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<Traffic> save(@Valid @RequestBody TrafficRequestDTO request) {
        return ResponseEntity.ok(trafficService.save(request));
    }

    @GetMapping("/record/{id}")
    public ResponseEntity<Traffic> getRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(trafficService.getById(id));
    }

    @PutMapping("/record/{id}")
    public ResponseEntity<Traffic> updateRecord(@PathVariable Long id, @Valid @RequestBody TrafficRequestDTO request) {
        return ResponseEntity.ok(trafficService.update(id, request));
    }

    @PatchMapping("/record/{id}/exit")
    public ResponseEntity<Traffic> exit(@PathVariable Long id) {
        return ResponseEntity.ok(trafficService.updateExit(id));
    }
    
 // Endpoint para actualizar solo campos específicos (salida, comentarios, próxima visita)
    @PatchMapping("/record/{id}")
    public ResponseEntity<Traffic> partialUpdate(@PathVariable Long id, @RequestBody TrafficUpdateDTO updateDTO) {
        return ResponseEntity.ok(trafficService.partialUpdate(id, updateDTO));
    }

    @DeleteMapping("/record/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trafficService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ============================================================
    // ENDPOINTS DE BÚSQUEDA
    // ============================================================

    @GetMapping("/records/phone/{phone}")
    public ResponseEntity<List<Traffic>> getRecordsByPhone(@PathVariable String phone) {
        List<Traffic> records = trafficService.getByPhone(phone);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/records/client")
    public ResponseEntity<List<Traffic>> getRecordsByClientName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("entryTime").descending());
        Page<Traffic> result = trafficService.getRecords(pageable, name);
        return ResponseEntity.ok(result.getContent());
    }

    @GetMapping("/check-phone/{phone}")
    public ResponseEntity<Map<String, Boolean>> checkPhone(@PathVariable String phone) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", !trafficService.getByPhone(phone).isEmpty());
        return ResponseEntity.ok(response);
    }

    // ============================================================
    // ENDPOINTS DE DATOS MAESTROS
    // ============================================================

    @GetMapping("/coaches")
    public ResponseEntity<List<Coach>> getCoaches() {
        return ResponseEntity.ok(trafficService.getCoaches());
    }

    @GetMapping("/models")
    public ResponseEntity<List<ToyotaModel>> getModels() {
        return ResponseEntity.ok(trafficService.getModels());
    }

    @GetMapping("/options/{category}")
    public ResponseEntity<List<SelectOption>> getOptions(@PathVariable String category) {
        return ResponseEntity.ok(trafficService.getOptions(category));
    }
}