package med.voll.api.patient;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.people.PeopleDTO;
import med.voll.api.people.PeopleJPA;

@Table(name= "patients")
@Entity(name= "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class PatientJPA extends PeopleJPA {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String cpf;

    public PatientJPA(@Valid PeopleDTO people, PatientDTO patient) {
        new PeopleJPA(people);
        this.cpf = patient.cpf();
    }
}
