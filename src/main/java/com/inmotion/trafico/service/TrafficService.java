package com.inmotion.trafico.service;

import com.inmotion.trafico.model.*;
import com.inmotion.trafico.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrafficService {

    private final TrafficRepository trafficRepository;
    private final CoachRepository coachRepository;
    private final ToyotaModelRepository toyotaModelRepository;
    private final SelectOptionRepository selectOptionRepository;

    @Transactional
    public Traffic save(TrafficRequestDTO request) {
        Traffic t = new Traffic();
        t.setClienteNombre(request.getFirstName() + " " + request.getLastName());
        t.setClienteTelefono(request.getPhone());
        t.setClienteEmail(request.getEmail());
        t.setCoachName(request.getCoachName());
        t.setGreeters(request.getGreeters());
        t.setAdv(request.getAdv());
        t.setEntryTime(LocalDateTime.now());
        t.setExitTime(request.getExitTime());
        t.setFirstVisit(false);
        t.setVisitNumber(1);
        t.setAppointment(parseBoolean(request.getAppointment()));
        t.setInternetOther(request.getInternetOther());
        t.setProspection(request.getProspection());
        t.setRequiredModel(request.getRequiredModel());
        t.setDemoEstatica(parseBoolean(request.getDemoEstatica()));
        t.setPruebaManejo(parseBoolean(request.getPruebaManejo()));
        t.setHojaOpciones(parseBoolean(request.getHojaOpciones()));
        t.setPlanFinanciamiento(parseBoolean(request.getPlanFinanciamiento()));
        t.setToyotour(parseBoolean(request.getToyotour()));
        t.setAutoActualAvaluo(parseBoolean(request.getAutoActualAvaluo()));
        t.setIntervinoCoach(parseBoolean(request.getIntervinoCoach()));
        t.setComments(request.getComments());
        t.setNextVisit(request.getNextVisit());
        return trafficRepository.save(t);
    }

    @Transactional
    public Traffic update(Long id, TrafficRequestDTO request) {
        Traffic t = getById(id);
        
        t.setClienteNombre(request.getFirstName() + " " + request.getLastName());
        t.setClienteTelefono(request.getPhone());
        t.setClienteEmail(request.getEmail());
        t.setCoachName(request.getCoachName());
        t.setGreeters(request.getGreeters());
        t.setAdv(request.getAdv());
        t.setExitTime(request.getExitTime());
        t.setAppointment(parseBoolean(request.getAppointment()));
        t.setInternetOther(request.getInternetOther());
        t.setProspection(request.getProspection());
        t.setRequiredModel(request.getRequiredModel());
        t.setDemoEstatica(parseBoolean(request.getDemoEstatica()));
        t.setPruebaManejo(parseBoolean(request.getPruebaManejo()));
        t.setHojaOpciones(parseBoolean(request.getHojaOpciones()));
        t.setPlanFinanciamiento(parseBoolean(request.getPlanFinanciamiento()));
        t.setToyotour(parseBoolean(request.getToyotour()));
        t.setAutoActualAvaluo(parseBoolean(request.getAutoActualAvaluo()));
        t.setIntervinoCoach(parseBoolean(request.getIntervinoCoach()));
        t.setComments(request.getComments());
        t.setNextVisit(request.getNextVisit());
        
        return trafficRepository.save(t);
    }

    @Transactional
    public Traffic updateExit(Long id) {
        Traffic t = getById(id);
        t.setExitTime(LocalDateTime.now());
        return trafficRepository.save(t);
    }

    @Transactional
    public Traffic partialUpdate(Long id, TrafficUpdateDTO updateDTO) {
        Traffic t = getById(id);
        
        if (updateDTO.getExitTime() != null) {
            t.setExitTime(updateDTO.getExitTime());
        }
        if (updateDTO.getComments() != null) {
            t.setComments(updateDTO.getComments());
        }
        if (updateDTO.getNextVisit() != null) {
            t.setNextVisit(updateDTO.getNextVisit());
        }
        
        return trafficRepository.save(t);
    }

    public Traffic getById(Long id) {
        return trafficRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado con ID: " + id));
    }

    public Page<Traffic> getRecords(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            return trafficRepository.searchAll(search, pageable);
        }
        List<Traffic> content = trafficRepository.findTodayVisits(pageable.getPageSize(), (int) pageable.getOffset());
        long total = trafficRepository.countTodayVisits();
        return new PageImpl<>(content, pageable, total);
    }

    public List<Traffic> getByPhone(String phone) {
        return trafficRepository.findByClienteTelefono(phone);
    }

    public long countToday() {
        return trafficRepository.countTodayVisits();
    }

    @Transactional
    public void delete(Long id) {
        trafficRepository.deleteById(id);
    }

    public List<Coach> getCoaches() {
        return coachRepository.findByActiveTrueOrderByName();
    }

    public List<ToyotaModel> getModels() {
        return toyotaModelRepository.findByActiveTrueOrderByName();
    }

    public List<SelectOption> getOptions(String category) {
        return selectOptionRepository.findByCategoryAndActiveTrueOrderByValue(category);
    }

    private Boolean parseBoolean(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        String trimmed = value.trim().toLowerCase();
        return trimmed.equals("true") || 
               trimmed.equals("1") || 
               trimmed.equals("yes") || 
               trimmed.equals("si") ||
               trimmed.equals("on") || 
               trimmed.equals("checked");
    }
}