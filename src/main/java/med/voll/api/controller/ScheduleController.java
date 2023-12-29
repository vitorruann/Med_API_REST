package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.schedule.AppointmentSchedule;
import med.voll.api.domain.schedule.NewScheduleDTO;
import med.voll.api.domain.schedule.ScheduleDTO;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private AppointmentSchedule appointmentSchedule;
    
    @PostMapping
    @Transactional
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<ScheduleDTO> storeSchedule(@RequestBody @Valid NewScheduleDTO schedule) {

        var dto = appointmentSchedule.schedule(schedule);

        return ResponseEntity.ok(dto);
    }
}
