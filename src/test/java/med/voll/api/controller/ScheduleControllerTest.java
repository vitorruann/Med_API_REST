package med.voll.api.controller;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import med.voll.api.domain.doctor.Specialties;
import med.voll.api.domain.schedule.AppointmentSchedule;
import med.voll.api.domain.schedule.NewScheduleDTO;
import med.voll.api.domain.schedule.ScheduleDTO;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ScheduleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<NewScheduleDTO> newScheduleDTOJson;

    @Autowired
    private JacksonTester<ScheduleDTO> scheduleDTOJson;

    @MockBean
    private AppointmentSchedule appointmentSchedule;

    @Test
    @DisplayName("Deve devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void testStoreScheduleHTTP400() throws Exception {
        var response = mvc.perform(MockMvcRequestBuilders
            .post("/schedules"))
            .andReturn()
            .getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve devolver codigo http 200 quando informacoes estao certas")
    @WithMockUser
    void testStoreScheduleHTTP200() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var newScheduleDTO = new NewScheduleDTO(1l, 2l, date, Specialties.CARDIOLOGIA);

        var scheduleDTO = new ScheduleDTO(null, 1l, 2l, date);
        Mockito.when(appointmentSchedule.schedule(Mockito.any())).thenReturn(scheduleDTO);

        var response = mvc.perform(MockMvcRequestBuilders
            .post(
                "/schedules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newScheduleDTOJson.write(newScheduleDTO).getJson())
            )
            .andReturn()
            .getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonExpected = scheduleDTOJson.write(scheduleDTO).getJson();

        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }
}
