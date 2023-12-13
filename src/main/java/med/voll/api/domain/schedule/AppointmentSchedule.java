package med.voll.api.domain.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.ValidationException;
import med.voll.api.domain.ValidationExepition;
import med.voll.api.domain.doctor.DoctorJPA;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.schedule.validations.ValidatorSchedule;

@Service
public class AppointmentSchedule {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private List<ValidatorSchedule> validators;
    
    public ScheduleDTO schedule(NewScheduleDTO scheduleDTO) {
        if (scheduleDTO.idDoctor() != null && !doctorRepository.existsById(scheduleDTO.idDoctor())) {
            throw new ValidationExepition("Médico informado não existe!");
        }

        if (!patientRepository.existsById(scheduleDTO.idPatient())) {
            throw new ValidationExepition("Paciente informado não existe!");
        }

        validators.forEach(v -> v.validate(scheduleDTO));

        var doctor = findDoctor(scheduleDTO);
        if (doctor == null) {
            throw new ValidationExepition("Não existe médico disponível nessa data");
        }

        var patient = patientRepository.getReferenceById(scheduleDTO.idPatient());

        var schedule = new ScheduleJPA(null, doctor, patient, scheduleDTO.date());

        scheduleRepository.save(schedule);
        
        return new ScheduleDTO(schedule);
    }

    private DoctorJPA findDoctor(NewScheduleDTO scheduleDTO) {
        if (scheduleDTO.idPatient() != null) {
            return doctorRepository.getReferenceById(scheduleDTO.idDoctor());
        }

        if (scheduleDTO.specialtie() == null) {
            throw new ValidationException("Especialidade obrigatória quando médico não for escolhido!");
        }

        return doctorRepository.findFreeDoctorOnDate(scheduleDTO.specialtie(), scheduleDTO.date());
    }
}
