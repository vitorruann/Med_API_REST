package med.voll.api.people;

import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.AddressJPA;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PeopleJPA {
    
    private String name;
    private String email;
    private String phone;

    @Embedded
    private AddressJPA address;

    public PeopleJPA(@Valid PeopleDTO people) {
        this.name = people.name();
        this.email = people.email();
        this.phone = people.phone();
        this.address = new AddressJPA(people.address());
    }

}
