package med.voll.api.domain.doctor;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<DoctorJPA, Long>{

    Page<DoctorJPA> findAllByActiveTrue(Pageable pageable);

    @Query("select d from Doctor d where d.active = true and d.specialties = :specialties and d.id not in(select s.doctor.id from Schedule s where s.date = :date) order by rand() limit 1")
    DoctorJPA findFreeDoctorOnDate(Specialties specialties, LocalDateTime date);
}
