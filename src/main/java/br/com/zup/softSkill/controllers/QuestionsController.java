package br.com.zup.softSkill.controllers;

import br.com.zup.softSkill.dtos.QuestionsRequestDTO;
import br.com.zup.softSkill.dtos.QuestionsResponseDTO;
import br.com.zup.softSkill.models.Questions;
import br.com.zup.softSkill.services.QuestionsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Questions")
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;

    @PostMapping
    public ResponseEntity<QuestionsResponseDTO> createQuestion(@RequestBody @Valid QuestionsRequestDTO requestDTO){ 
        Questions question = new Questions(null, requestDTO.getTitle(), requestDTO.getOptionA(), requestDTO.getOptionB(), requestDTO.getOptionC());
        Questions createdQuestion = questionsService.createQuestion(question);

        QuestionsResponseDTO responseDTO = new QuestionsResponseDTO();
        responseDTO.setId(createdQuestion.getId());
        responseDTO.setTitle(createdQuestion.getTitle());
        responseDTO.setOptionA(createdQuestion.getOptionA());
        responseDTO.setOptionB(createdQuestion.getOptionB());
        responseDTO.setOptionC(createdQuestion.getOptionC());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionsResponseDTO> updateQuestion(@PathVariable UUID id, @RequestBody @Valid QuestionsRequestDTO requestDTO) {
        Questions updatedQuestion = questionsService.updateQuestion(id, new Questions(id, requestDTO.getTitle(), requestDTO.getOptionA(), requestDTO.getOptionB(), requestDTO.getOptionC()));

        if (updatedQuestion != null) {
            QuestionsResponseDTO responseDTO = new QuestionsResponseDTO();
            responseDTO.setId(updatedQuestion.getId());
            responseDTO.setTitle(updatedQuestion.getTitle());
            responseDTO.setOptionA(updatedQuestion.getOptionA());
            responseDTO.setOptionB(updatedQuestion.getOptionB());
            responseDTO.setOptionC(updatedQuestion.getOptionC());
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   @GetMapping("/list/{id}")
    public ResponseEntity<List<QuestionsResponseDTO>> listQuestions() {
        List<QuestionsResponseDTO> responseDTOList = questionsService.listQuestions().stream().map(question -> {
            QuestionsResponseDTO responseDTO = new QuestionsResponseDTO();
            responseDTO.setId(question.getId());
            responseDTO.setTitle(question.getTitle());
            responseDTO.setOptionA(question.getOptionA());
            responseDTO.setOptionB(question.getOptionB());
            responseDTO.setOptionC(question.getOptionC());
            return responseDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionsResponseDTO> getQuestionById(@PathVariable UUID id) {
        Questions question = questionsService.getQuestionById(id);
        if (question != null) {
            QuestionsResponseDTO responseDTO = new QuestionsResponseDTO();
            responseDTO.setId(question.getId());
            responseDTO.setTitle(question.getTitle());
            responseDTO.setOptionA(question.getOptionA());
            responseDTO.setOptionB(question.getOptionB());
            responseDTO.setOptionC(question.getOptionC());
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable UUID id) {
        questionsService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
