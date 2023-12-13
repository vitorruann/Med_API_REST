package med.voll.api.domain.schedule.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidationExepition;
import med.voll.api.domain.schedule.NewScheduleDTO;
import med.voll.api.domain.schedule.ScheduleRepository;

@Component
public class ValidatorDoctorAvailable implements ValidatorSchedule {

    @Autowired
    private ScheduleRepository scheduleRepository;
    
    public void validate(NewScheduleDTO schedule) {

        var isDoctorAvailable = scheduleRepository.existsByDoctorIdAndDate(schedule.idDoctor(), schedule.date());
        if (isDoctorAvailable) {
            throw new ValidationExepition("Médico não disponível neste horário");
        }

    }
}
