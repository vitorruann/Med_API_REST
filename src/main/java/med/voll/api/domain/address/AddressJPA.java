package med.voll.api.domain.address;

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

    public void update(AddressDTO address) {
        
        if (address.publicArea() != null) {
            this.publicArea = address.publicArea();
        }
        if (address.neighborhood() != null) {
            this.neighborhood = address.neighborhood();
        }
        if (address.cep() != null) {
            this.cep = address.cep();
        }
        if (address.uf() != null) {
            this.uf = address.uf();
        }
        if (address.city() != null) {
            this.city = address.city();
        }
        if (address.number() != null) {
            this.number = address.number();
        }
        if (address.complement() != null) {
            this.complement = address.complement();
        }

    }
}
