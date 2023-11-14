package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.patient.NewPatientDTO;
import med.voll.api.patient.PatientJPA;
import med.voll.api.patient.PatientRepository;
import med.voll.api.patient.ShowPatientDTO;

@RestController
@RequestMapping("/patients")
public class PatientController {
    
    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void newPatient(@RequestBody @Valid NewPatientDTO patient) {
        repository.save(new PatientJPA(patient));
    }

    @GetMapping
    public Page<ShowPatientDTO> showPatient(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return repository.findAll(pageable).map(ShowPatientDTO::new);
    }
}
