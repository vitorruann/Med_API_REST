package med.voll.api.model.doctor;

import med.voll.api.model.address.ResidentOfAddress;

public record ModelDoctor(String name, String email, String crm, Specialties specialties, ResidentOfAddress address) {
}
