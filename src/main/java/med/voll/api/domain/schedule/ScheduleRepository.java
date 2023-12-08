package med.voll.api.domain.schedule;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleJPA, Long>{

    boolean existsByDoctorIdAndDate(Long idDoctor, LocalDateTime date);

    boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime operatingTime, LocalDateTime closingTime);
}
