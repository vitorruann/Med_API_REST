package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import med.voll.api.patient.*;

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
        return repository.findAllByActiveTrue(pageable).map(ShowPatientDTO::new);
    }

    @PutMapping
    @Transactional
    public void updatePatient(@RequestBody @Valid UpdatePatientDTO patient) {
        var oldPatient = repository.getReferenceById(patient.id());
        oldPatient.update(patient);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletePatient(@PathVariable Long id) {
        var doctro = repository.getReferenceById(id);
        doctro.delete();
    }
}
