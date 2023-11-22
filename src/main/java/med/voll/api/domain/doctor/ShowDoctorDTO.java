package med.voll.api.domain.doctor;

public record ShowDoctorDTO(Long id, String name, String email, String crm, Specialties specialties) {    

    public ShowDoctorDTO(DoctorJPA doctor) {
        this(doctor.getId(), doctor.getName(),doctor.getEmail(), doctor.getCrm(), doctor.getSpecialties());
    }

}
