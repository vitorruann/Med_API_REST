package med.voll.api.domain.schedule;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record NewScheduleDTO(

    Long doctorId,

    @NotNull 
    Long patientId, 
    
    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime date
    
    ) {
}
