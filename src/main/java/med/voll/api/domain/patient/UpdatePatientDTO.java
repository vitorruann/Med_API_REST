package med.voll.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressDTO;

public record UpdatePatientDTO(

    @NotNull Long id,
    String name,
    String phone,
    AddressDTO address

) {
}
