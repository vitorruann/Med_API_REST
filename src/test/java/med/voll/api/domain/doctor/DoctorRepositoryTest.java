package med.voll.api.domain.doctor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import med.voll.api.domain.address.AddressDTO;
import med.voll.api.domain.patient.NewPatientDTO;
import med.voll.api.domain.patient.PatientJPA;
import med.voll.api.domain.schedule.ScheduleJPA;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.assertj.core.api.Assertions;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    @DisplayName("Deve devolver null quando unico medico cadastrado nao esta disponivel na data")
    void findFreeDoctorOnDateScenario1() {
        var nextMondayAt10 = LocalDate.now()
                                .with(TemporalAdjusters
                                    .next(DayOfWeek.MONDAY))
                                .atTime(10, 0);
        var doctor = createDoctor("Medico", "medico@medico.com", "123456", Specialties.CARDIOLOGIA);
        var patient = createPatient("Paciente", "paciente@paciente.com", "00000000000");
        createSchedule(doctor, patient, nextMondayAt10);
        var doctorAvailable = doctorRepository.findFreeDoctorOnDate(Specialties.CARDIOLOGIA, nextMondayAt10);

        Assertions.assertThat(doctorAvailable).isNull();
    }

    @Test
    @DisplayName("Deve devolver medico quando ele estiver disponivel na data")
    void findFreeDoctorOnDateScenario2() {
        var nextMondayAt10 = LocalDate.now()
                                .with(TemporalAdjusters
                                    .next(DayOfWeek.MONDAY))
                                .atTime(10, 0);
        var doctor = createDoctor("Medico", "medico@medico.com", "123456", Specialties.CARDIOLOGIA);

        var doctorAvailable = doctorRepository.findFreeDoctorOnDate(Specialties.CARDIOLOGIA, nextMondayAt10);

        Assertions.assertThat(doctorAvailable).isEqualTo(doctor);
    }
    
    private void createSchedule(DoctorJPA doctor, PatientJPA patient, LocalDateTime date) {
        entityManager.persist(new ScheduleJPA(null, doctor, patient, date));
    }

    private DoctorJPA createDoctor(String name, String email, String crm, Specialties specialties) {
        var doctor = new DoctorJPA(doctorDTO(name, email, crm, specialties));
        entityManager.persist(doctor);
        return doctor;
    }

    private PatientJPA createPatient(String name, String email, String cpf) {
        var patient = new PatientJPA(patientDTO(name, email, cpf));
        entityManager.persist(patient);
        return patient;
    }

    private NewDoctorDTO doctorDTO(String name, String email, String crm, Specialties specialties) {
        return new NewDoctorDTO(name, email, email, crm, specialties, addressDTO());
    }

    private NewPatientDTO patientDTO(String name, String email, String cpf) {
        return new NewPatientDTO(name, email, "48999999999", cpf, addressDTO());
    }

    private AddressDTO addressDTO() {
        return new AddressDTO("Rua Muito Legal", "Bairro Bom", "0000000", "Floripa", "SC", null, null);
    }
}
