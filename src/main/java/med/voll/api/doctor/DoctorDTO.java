package med.voll.api.doctor;

import med.voll.api.address.AddressJPA;

public record DoctorDTO(Long id, String name, String email, String phone, String crm, Specialties specialties, AddressJPA address) {

    public DoctorDTO(DoctorJPA newDoctor) {

        this(
        newDoctor.getId(),
        newDoctor.getName(),
        newDoctor.getEmail(),
        newDoctor.getPhone(),
        newDoctor.getCrm(),
        newDoctor.getSpecialties(),
        newDoctor.getAddress());

    }

}
