package med.voll.api.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<PatientJPA, Long>{

    Page<PatientJPA> findAllByActiveTrue(Pageable pageable);

    @Query("""
        SELECT p.active 
        FROM Patient p
        WHERE p.id = :idPatient
        """)
    boolean findActiveById(Long idPatient);
}
