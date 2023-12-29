package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.doctor.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    
    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<DoctorDTO> storeDoctor(@RequestBody @Valid NewDoctorDTO doctor, UriComponentsBuilder uriBuilder) {
        var newDoctor = new DoctorJPA(doctor);
        repository.save(newDoctor);

        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(newDoctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDTO(newDoctor));
    }

    @GetMapping
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<Page<ShowDoctorDTO>> showDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var page = repository.findAllByActiveTrue(pageable).map(ShowDoctorDTO::new);
        
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<DoctorDTO> showDoctorById(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);

        return ResponseEntity.ok(new DoctorDTO(doctor));
    }

    @PutMapping
    @Transactional
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<DoctorDTO> updateDoctor(@RequestBody @Valid UpdateDoctorDTO doctor) {
        var updatedDoctor = repository.getReferenceById(doctor.id());
        updatedDoctor.update(doctor);

        return ResponseEntity.ok(new DoctorDTO(updatedDoctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<String> destroyDoctor(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();

        return ResponseEntity.noContent().build();
    }
}
