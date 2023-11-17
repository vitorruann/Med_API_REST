package med.voll.api.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.AddressDTO;

public record UpdatePatientDTO(

    @NotNull Long id,
    String name,
    String phone,
    AddressDTO address

) {
}
