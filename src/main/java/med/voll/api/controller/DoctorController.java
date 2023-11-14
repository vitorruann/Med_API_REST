package med.voll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.doctor.DoctorJPA;
import med.voll.api.doctor.DoctorRepository;
import med.voll.api.doctor.NewDoctorDTO;
import med.voll.api.doctor.ShowDoctorDTO;
import med.voll.api.doctor.UpdateDoctorDTO;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    
    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void newDoctor(@RequestBody @Valid NewDoctorDTO doctor) {
        System.out.println(doctor);
        repository.save(new DoctorJPA(doctor));
    }

    @GetMapping
    public Page<ShowDoctorDTO> showDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return repository.findAll(pageable).map(ShowDoctorDTO::new);
    }

    @PutMapping
    @Transactional
    public void UpdateDoctor(@RequestBody @Valid UpdateDoctorDTO doctor) {
        var oldDoctor = repository.getReferenceById(doctor.id());
        oldDoctor.update(doctor);
    }
}
