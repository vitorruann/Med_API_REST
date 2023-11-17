package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import med.voll.api.doctor.*;

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
        return repository.findAllByActiveTrue(pageable).map(ShowDoctorDTO::new);
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid UpdateDoctorDTO doctor) {
        var oldDoctor = repository.getReferenceById(doctor.id());
        oldDoctor.update(doctor);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();
    }
}
