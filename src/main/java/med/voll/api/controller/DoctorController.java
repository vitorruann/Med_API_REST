package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.doctor.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    
    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorDTO> newDoctor(@RequestBody @Valid NewDoctorDTO doctor, UriComponentsBuilder uriBuilder) {
        var newDoctor = new DoctorJPA(doctor);
        repository.save(newDoctor);

        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(newDoctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDTO(newDoctor));
    }

    @GetMapping
    public ResponseEntity<Page<ShowDoctorDTO>> showDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var page = repository.findAllByActiveTrue(pageable).map(ShowDoctorDTO::new);
        
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDTO> updateDoctor(@RequestBody @Valid UpdateDoctorDTO doctor) {
        var updatedDoctor = repository.getReferenceById(doctor.id());
        updatedDoctor.update(doctor);

        return ResponseEntity.ok(new DoctorDTO(updatedDoctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();

        return ResponseEntity.noContent().build();
    }
}
