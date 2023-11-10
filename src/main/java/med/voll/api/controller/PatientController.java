package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.patient.PatientDTO;
import med.voll.api.patient.PatientJPA;
import med.voll.api.patient.PatientRepository;
import med.voll.api.people.PeopleDTO;
import med.voll.api.people.PeopleJPA;

@RestController
@RequestMapping("/patients")
public class PatientController {
    
    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void newPatient(@RequestBody @Valid PeopleDTO people, PatientDTO patient) {
        System.out.println(patient);        
        System.out.println(people);

       
        PeopleJPA p = new PatientJPA(people, patient);
        System.out.println(p.getName());

        // repository.save(new PatientJPA());
    }

}
