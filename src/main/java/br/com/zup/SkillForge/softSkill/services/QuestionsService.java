package br.com.zup.SkillForge.softSkill.services;

import br.com.zup.SkillForge.infras.ResourceNotFoundException;
import br.com.zup.SkillForge.softSkill.dtos.QuestionsRequestDTO;
import br.com.zup.SkillForge.softSkill.dtos.QuestionsResponseDTO;
import br.com.zup.SkillForge.softSkill.models.Questions;
import br.com.zup.SkillForge.softSkill.repositories.QuestionsRepository;
import br.com.zup.SkillForge.softSkill.services.mappers.QuestionsMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionsService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionsService.class);

    private final QuestionsRepository questionsRepository;
    private final QuestionsMapper questionsMapper;

    public QuestionsService(QuestionsRepository questionsRepository, QuestionsMapper questionsMapper) {
        this.questionsRepository = questionsRepository;
        this.questionsMapper = questionsMapper;
    }

    public QuestionsResponseDTO createQuestion(QuestionsRequestDTO request) {
        logger.info("Creating a new question");
        Questions question = questionsMapper.toModel(request);
        question = questionsRepository.save(question);
        return questionsMapper.toDto(question);
    }

    public QuestionsResponseDTO updateQuestion(Long id, QuestionsRequestDTO updatedRequest) {
        logger.info("Updating question with id: {}", id);
        return questionsRepository.findById(id)
                .map(existingQuestion -> {
                    existingQuestion.setTitle(updatedRequest.getTitle());
                    existingQuestion.setOptionA(updatedRequest.getOptionA());
                    existingQuestion.setOptionB(updatedRequest.getOptionB());
                    existingQuestion.setOptionC(updatedRequest.getOptionC());
                    Questions updatedQuestion = questionsRepository.save(existingQuestion);
                    return questionsMapper.toDto(updatedQuestion);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + id));
    }

    public void deleteQuestion(Long id) {
        logger.info("Deleting question with id: {}", id);
        if (!questionsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Question not found with id " + id);
        }
        questionsRepository.deleteById(id);
    }

    public List<QuestionsResponseDTO> listQuestions() {
        logger.info("Listing all questions");
        return questionsRepository.findAll().stream()
                .map(questionsMapper::toDto)
                .collect(Collectors.toList());
    }


}