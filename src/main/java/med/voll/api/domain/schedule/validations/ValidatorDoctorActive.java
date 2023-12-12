package med.voll.api.domain.schedule.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidationExepition;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.schedule.NewScheduleDTO;

@Component
public class ValidatorDoctorActive implements ValidatorSchedule {

    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(NewScheduleDTO schedule) {

        if (schedule.idDoctor() == null) {
            return;
        }

        var isDoctorActive = doctorRepository.findActiveById(schedule.idDoctor());

        if (!isDoctorActive) {
            throw new ValidationExepition("Consulta não pode ser agendada com médico inativo");
        }

    }
}
