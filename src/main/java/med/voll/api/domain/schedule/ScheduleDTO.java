package med.voll.api.domain.schedule;

import java.time.LocalDateTime;

public record ScheduleDTO(Long id, Long idDoctor, Long idPatient, LocalDateTime date) {
}
