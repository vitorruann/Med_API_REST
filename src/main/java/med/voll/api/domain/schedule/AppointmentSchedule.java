package med.voll.api.domain.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.ValidationException;
import med.voll.api.domain.ValidationExepition;
import med.voll.api.domain.doctor.DoctorJPA;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;

@Service
public class AppointmentSchedule {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    
    public void schedule(NewScheduleDTO scheduleDTO) {
        if (scheduleDTO.doctorId() != null && !doctorRepository.existsById(scheduleDTO.patientId())) {
            throw new ValidationExepition("Médico informado não existe!");
        }

        if (!patientRepository.existsById(scheduleDTO.patientId())) {
            throw new ValidationExepition("Paciente informado não existe!");
        }

        var doctor = findDoctor(scheduleDTO);
        var patient = patientRepository.getReferenceById(scheduleDTO.patientId());

        var schedule = new ScheduleJPA(null, doctor, patient, scheduleDTO.date());

        scheduleRepository.save(null);
    }

    private DoctorJPA findDoctor(NewScheduleDTO scheduleDTO) {
        if (scheduleDTO.doctorId() != null) {
            return doctorRepository.getReferenceById(scheduleDTO.doctorId());
        }

        if (scheduleDTO.specialtie() == null) {
            throw new ValidationException("Especialidade obrigatória quando médico não for escolhido!");
        }

        return doctorRepository.findFreeDoctorOnDate(scheduleDTO.specialtie(), scheduleDTO.date());
    }
}
