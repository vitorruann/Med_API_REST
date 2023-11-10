package med.voll.api.people;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.address.AddressDTO;

public record PeopleDTO(

    @NotBlank String name,
    @NotBlank @Email String email,
    @NotBlank String phone,
    @NotNull @Valid AddressDTO address

) {
}
