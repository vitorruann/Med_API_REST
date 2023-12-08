package med.voll.api.domain.schedule.validations;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

import med.voll.api.domain.ValidationExepition;
import med.voll.api.domain.schedule.ScheduleDTO;

public class ValidatorScheduleTime {
    
    private int OPENNING_TIME = 7;
    private int CLOSING_TIME = 18;
    private int MINIMUM_TIME_DIFFERENCE = 30;

    public void operatingTime(ScheduleDTO schedule) {

        var sunday = schedule.date().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpenningTime = schedule.date().getHour() < OPENNING_TIME;
        var afterClosingTime = schedule.date().getHour() > CLOSING_TIME;

        if (sunday || beforeOpenningTime || afterClosingTime) {
            throw new ValidationExepition("Consulta fora do horário de funcionamento da clínica");
        }
        
    }

    public void closeCurrentTime(ScheduleDTO schedule) {

        var currentTime = LocalDateTime.now();
        var differenceInMinutes = Duration.between(currentTime, schedule.date()).toMinutes();

        if (differenceInMinutes < MINIMUM_TIME_DIFFERENCE) {
            throw new ValidationExepition("Consulta deve ser agendada com no mínimo 30 minutos de antecedência");
        }
        
    }

}
