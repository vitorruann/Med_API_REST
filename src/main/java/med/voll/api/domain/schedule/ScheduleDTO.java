package med.voll.api.domain.schedule;

import java.time.LocalDateTime;

public record ScheduleDTO(Long id, Long idDoctor, Long idPatient, LocalDateTime date) {

    public ScheduleDTO(ScheduleJPA schedule) {
        this(schedule.getId(), schedule.getDoctor().getId(), schedule.getPatient().getId(), schedule.getDate());
    }
}
