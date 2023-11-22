package med.voll.api.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.AddressDTO;

public record NewDoctorDTO(

    @NotBlank(message = "{name.not-blank}") String name,
    @NotBlank(message = "{email.not-blank}") @Email(message = "{name.invalid}") String email,
    @NotBlank(message = "{phone.not-blank}") String phone,
    @NotBlank(message = "{crm.not-blank}") @Pattern(regexp = "\\d{4,6}", message = "{name.not-blank}") String crm,
    @NotNull(message = "{specialties.not-blank}") Specialties specialties,
    @NotNull(message = "{address.not-blank}") @Valid AddressDTO address
    
    ) {
}
