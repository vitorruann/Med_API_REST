package med.voll.api.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.address.AddressDTO;

public record NewDoctorDTO(

    @NotBlank String name,
    @NotBlank @Email String email,
    @NotBlank String phone,
    @NotBlank @Pattern(regexp = "\\d{4,6}") String crm,
    @NotNull Specialties specialties,
    @NotNull @Valid AddressDTO address
    
    ) {
}
