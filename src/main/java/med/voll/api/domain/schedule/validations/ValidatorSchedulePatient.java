package med.voll.api.domain.schedule.validations;

import med.voll.api.domain.ValidationExepition;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.schedule.ScheduleDTO;
import med.voll.api.domain.schedule.ScheduleRepository;

public class ValidatorSchedulePatient {

        private int OPENNING_TIME = 7;
        private int CLOSING_TIME = 18;

        private PatientRepository patientRepository;
        private ScheduleRepository scheduleRepository;
    
        public void patientIsActive(ScheduleDTO schedule) {

        var isPatientActive = patientRepository.findActiveById(schedule.idPatient());
        if (!isPatientActive) {
            throw new ValidationExepition("Consulta não pode ser agendada com paciente inativo");
        }

    }

    public void patientAvailable(ScheduleDTO schedule) {

        var operatingTime = schedule.date().withHour(OPENNING_TIME);
        var closingTime = schedule.date().withHour(CLOSING_TIME);
        var isPatientAvailable = scheduleRepository.existsByPatientIdAndDateBetween(schedule.idPatient(), operatingTime, closingTime);
        if (!isPatientAvailable) {
            throw new ValidationExepition("Paciente já possui uma consulta neste mesmo horário");
        }

    }
}
