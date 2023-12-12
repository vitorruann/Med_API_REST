package med.voll.api.domain.schedule;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Specialties;

public record NewScheduleDTO(

    Long idDoctor,

    @NotNull 
    Long idPatient, 
    
    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime date,

    Specialties specialtie
    
    ) {
}
