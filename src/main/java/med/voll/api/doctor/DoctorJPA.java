package med.voll.api.doctor;

import med.voll.api.address.AddressJPA;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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
    private String phone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialties specialties;

    @Embedded
    private AddressJPA address;

    public DoctorJPA(NewDoctorDTO doctor) {

        this.name = doctor.name();
        this.email = doctor.email();
        this.phone = doctor.phone();
        this.crm = doctor.crm();
        this.specialties = doctor.specialties();
        this.address = new AddressJPA(doctor.address());
        
    }

    public void update(@Valid UpdateDoctorDTO doctor) {

        if (doctor.name() != null) {
            this.name = doctor.name();
        }
        if (doctor.phone() != null) {
            this.phone = doctor.phone();
        }
        if (doctor.address() != null) {
            this.address.update(doctor.address());
        }
    }
}
