package med.voll.api.patient;

public record ShowPatientDTO(Long id, String name, String cpf, String email) {

    public ShowPatientDTO(PatientJPA patient){
        this(patient.getId(), patient.getName(), patient.getCpf(), patient.getEmail());
    }

}
