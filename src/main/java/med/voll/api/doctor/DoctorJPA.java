package med.voll.api.doctor;

import med.voll.api.address.AddressJPA;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name= "doctors")
@Entity(name= "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class DoctorJPA {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialties specialties;

    @Embedded
    private AddressJPA address;

    public DoctorJPA(DoctorDTO doctor) {

        this.name = doctor.name();
        this.email = doctor.email();
        this.crm = doctor.crm();
        this.specialties = doctor.specialties();
        this.address = new AddressJPA(doctor.address());
        
    }
}
