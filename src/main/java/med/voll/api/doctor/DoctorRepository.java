package med.voll.api.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorJPA, Long>{

    Page<DoctorJPA> findAllByActiveTrue(Pageable pageable);
}
