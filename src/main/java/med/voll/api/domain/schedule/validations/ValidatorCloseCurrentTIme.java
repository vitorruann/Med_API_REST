package med.voll.api.domain.schedule.validations;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidationExepition;
import med.voll.api.domain.schedule.NewScheduleDTO;

@Component
public class ValidatorCloseCurrentTIme implements ValidatorSchedule {
    
    private int MINIMUM_TIME_DIFFERENCE = 30;

    public void validate(NewScheduleDTO schedule) {

        var currentTime = LocalDateTime.now();
        var differenceInMinutes = Duration.between(currentTime, schedule.date()).toMinutes();

        if (differenceInMinutes < MINIMUM_TIME_DIFFERENCE) {
            throw new ValidationExepition("Consulta deve ser agendada com no mínimo 30 minutos de antecedência");
        }
        
    }
}
