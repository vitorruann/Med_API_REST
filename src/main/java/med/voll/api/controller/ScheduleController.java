package med.voll.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.schedule.NewScheduleDTO;
import med.voll.api.domain.schedule.ScheduleDTO;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    
    @PostMapping
    @Transactional
    public ResponseEntity<ScheduleDTO> storeSchedule(@RequestBody @Valid NewScheduleDTO schedule) {
        System.out.println(schedule);
        return ResponseEntity.ok(new ScheduleDTO(null, null, null, null));
    }
}
