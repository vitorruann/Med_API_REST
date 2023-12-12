package med.voll.api.domain.schedule.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidationExepition;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.schedule.NewScheduleDTO;

@Component
public class ValidatorPatientActive implements ValidatorSchedule {


    @Autowired
    private PatientRepository patientRepository;
    
    public void validate(NewScheduleDTO schedule) {

        var isPatientActive = patientRepository.findActiveById(schedule.idPatient());
        if (!isPatientActive) {
            throw new ValidationExepition("Consulta não pode ser agendada com paciente inativo");
        }

    }
}
