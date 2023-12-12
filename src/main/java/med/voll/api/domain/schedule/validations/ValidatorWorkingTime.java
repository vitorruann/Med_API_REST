package med.voll.api.domain.schedule.validations;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidationExepition;
import med.voll.api.domain.schedule.NewScheduleDTO;

@Component
public class ValidatorWorkingTime implements ValidatorSchedule {
    
    private int OPENNING_TIME = 7;
    private int CLOSING_TIME = 18;

    public void validate(NewScheduleDTO schedule) {

        var sunday = schedule.date().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpenningTime = schedule.date().getHour() < OPENNING_TIME;
        var afterClosingTime = schedule.date().getHour() > CLOSING_TIME;

        if (sunday || beforeOpenningTime || afterClosingTime) {
            throw new ValidationExepition("Consulta fora do horário de funcionamento da clínica");
        }
        
    }
}
