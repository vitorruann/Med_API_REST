package med.voll.api.doctor;

import med.voll.api.address.AddressDTO;

public record DoctorDTO(String name, String email, String crm, Specialties specialties, AddressDTO address) {
}
