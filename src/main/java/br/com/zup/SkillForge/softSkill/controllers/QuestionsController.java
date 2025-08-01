package br.com.zup.SkillForge.softSkill.controllers;

import br.com.zup.SkillForge.softSkill.dtos.QuestionsRequestDTO;
import br.com.zup.SkillForge.softSkill.dtos.QuestionsResponseDTO;
import br.com.zup.SkillForge.softSkill.services.QuestionsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inclusion-questions")
@RequiredArgsConstructor
public class QuestionsController {

    private final QuestionsService questionsService;

    @PostMapping
    public ResponseEntity<QuestionsResponseDTO> create(@RequestBody @Valid QuestionsRequestDTO requestDTO) {
        QuestionsResponseDTO responseDTO = questionsService.createQuestion(requestDTO);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionsResponseDTO> update(@PathVariable Long id, @RequestBody @Valid QuestionsRequestDTO requestDTO) {
        QuestionsResponseDTO responseDTO = questionsService.updateQuestion(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<QuestionsResponseDTO>> listQuestions() {
        List<QuestionsResponseDTO> responseDTOList = questionsService.listQuestions();
        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionsResponseDTO> getQuestionById(@PathVariable Long id) {
        QuestionsResponseDTO responseDTO = questionsService.getQuestionById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        questionsService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
