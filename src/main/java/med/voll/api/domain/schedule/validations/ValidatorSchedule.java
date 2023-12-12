package med.voll.api.domain.schedule.validations;

import med.voll.api.domain.schedule.NewScheduleDTO;

public interface ValidatorSchedule {

    void validate(NewScheduleDTO scheduleDTO);

}
