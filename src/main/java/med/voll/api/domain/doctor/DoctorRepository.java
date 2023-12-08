package med.voll.api.domain.doctor;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<DoctorJPA, Long>{

    Page<DoctorJPA> findAllByActiveTrue(Pageable pageable);

    @Query("""
            SELECT d 
            FROM Doctor d
            WHERE d.active = true 
            AND d.specialties = :specialties
            AND d.id not in(
                SELECT s.doctor.id 
                FROM Schedule s
                WHERE s.date = :date
            )
            ORDER BY rand()
            LIMIT 1
            """)
    DoctorJPA findFreeDoctorOnDate(Specialties specialties, LocalDateTime date);

    @Query("""
            SELECT d.active 
            FROM Doctor d
            WHERE d.id = :idDoctor
            """)
    boolean findActiveById(Long idDoctor);
}
