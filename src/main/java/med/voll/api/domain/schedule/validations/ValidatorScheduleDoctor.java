package med.voll.api.domain.schedule.validations;

import med.voll.api.domain.ValidationExepition;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.schedule.ScheduleDTO;
import med.voll.api.domain.schedule.ScheduleRepository;

public class ValidatorScheduleDoctor {

    private DoctorRepository doctorRepository;
    private ScheduleRepository scheduleRepository;

    public void doctorIsActive(ScheduleDTO schedule) {

        if (schedule.idDoctor() == null) {
            return;
        }

        var isDoctorActive = doctorRepository.findActiveById(schedule.idDoctor());

        if (!isDoctorActive) {
            throw new ValidationExepition("Consulta não pode ser agendada com médico inativo");
        }

    }

    public void doctorAvailable(ScheduleDTO schedule) {

        var isDoctorAvailable = scheduleRepository.existsByDoctorIdAndDate(schedule.idDoctor(), schedule.date());
        if (!isDoctorAvailable) {
            throw new ValidationExepition("Médico não disponível neste horário");
        }

    }
    
}
