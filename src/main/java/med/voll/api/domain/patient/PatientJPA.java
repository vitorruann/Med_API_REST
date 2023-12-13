package med.voll.api.domain.patient;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.AddressJPA;

@Table(name= "patients")
@Entity(name= "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class PatientJPA {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private String phone;
    private String cpf;

    @Embedded
    private AddressJPA address;

    private Boolean active;

    public PatientJPA(@Valid NewPatientDTO patient) {

        this.name = patient.name();
        this.email = patient.email();
        this.phone = patient.phone();
        this.cpf = patient.cpf();
        this.address = new AddressJPA(patient.address());
        this.active = true;

    }

    public void update(@Valid UpdatePatientDTO patient) {
        if (patient.name() != null) {
            this.name = patient.name();
        }
        if (patient.phone() != null) {
            this.phone = patient.phone();
        }
        if (patient.address() != null) {
            this.address.update(patient.address());
        }
    }

    public void delete() {
        this.active = false;
    }
}
