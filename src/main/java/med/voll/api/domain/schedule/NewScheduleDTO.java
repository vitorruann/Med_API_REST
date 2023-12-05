package med.voll.api.domain.schedule;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record NewScheduleDTO(

    Long doctorId,

    @NotNull 
    Long patientId, 
    
    @NotNull
    @Future
    LocalDateTime date
    
    ) {
}
