package med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressJPA {

    private String publicArea;
    private String neighborhood;
    private String cep;
    private String number;
    private String complement;
    private String city;
    private String uf;
    
    public AddressJPA(AddressDTO address) {
        
        this.publicArea = address.publicArea();
        this.neighborhood = address.neighborhood();
        this.cep = address.cep();
        this.uf = address.uf();
        this.city = address.city();
        this.number = address.number();
        this.complement = address.complement();

    }
}
