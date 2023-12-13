package med.voll.api.domain.schedule.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidationExepition;
import med.voll.api.domain.schedule.NewScheduleDTO;
import med.voll.api.domain.schedule.ScheduleRepository;

@Component
public class ValidatorPatientAvailable implements ValidatorSchedule {
    
    private int OPENNING_TIME = 7;
    private int CLOSING_TIME = 18;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public void validate(NewScheduleDTO schedule) {

        var operatingTime = schedule.date().withHour(OPENNING_TIME);
        var closingTime = schedule.date().withHour(CLOSING_TIME);
        var isPatientAvailable = scheduleRepository.existsByPatientIdAndDateBetween(schedule.idPatient(), operatingTime, closingTime);
        if (isPatientAvailable) {
            throw new ValidationExepition("Paciente já possui uma consulta neste mesmo horário");
        }

    }
}
