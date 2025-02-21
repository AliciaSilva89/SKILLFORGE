package br.com.zup.SkillForge.questionsTest;

import br.com.zup.softSkill.controllers.QuestionsController;
import br.com.zup.softSkill.dtos.QuestionsRequestDTO;
import br.com.zup.softSkill.dtos.QuestionsResponseDTO;
import br.com.zup.softSkill.models.Questions;
import br.com.zup.softSkill.services.QuestionsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuestionsController.class)
public class QuestionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionsService questionsService;

    @Autowired
    private ObjectMapper objectMapper;

    private QuestionsRequestDTO requestDTO;
    private QuestionsResponseDTO responseDTO;

    @BeforeEach
    public void setUp() {
        requestDTO = new QuestionsRequestDTO();
        requestDTO.setTitle("Qual é a importância de reconhecer a diversidade racial em uma sociedade?");
        requestDTO.setOptionA("Promove igualdade e respeito.");
        requestDTO.setOptionB("Não faz diferença.");
        requestDTO.setOptionC("Cria divisões desnecessárias.");

        // Prepare the responseDTO to match what is returned by the service
        responseDTO = new QuestionsResponseDTO();
        responseDTO.setId(UUID.randomUUID());
        responseDTO.setTitle(requestDTO.getTitle());
        responseDTO.setOptionA(requestDTO.getOptionA());
        responseDTO.setOptionB(requestDTO.getOptionB());
        responseDTO.setOptionC(requestDTO.getOptionC());

        // Mock the behavior of the service (what it should return when called)
        Questions question = new Questions(responseDTO.getId(), requestDTO.getTitle(), requestDTO.getOptionA(), requestDTO.getOptionB(), requestDTO.getOptionC());
        when(questionsService.createQuestion(any(Questions.class))).thenReturn(question);
    }

    @Test
    public void testCreateQuestion() throws Exception {
        // Perform the POST request with requestDTO as the body of the request
        mockMvc.perform(post("/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))  // Send requestDTO in the body
                .andExpect(status().isCreated())  // Expect status 201 Created
                .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));  // Verify the response matches responseDTO
    }
}
