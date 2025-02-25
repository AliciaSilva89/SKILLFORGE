package br.com.zup.SkillForge.softSkill.services;

import br.com.zup.SkillForge.infras.ResourceNotFoundException;
import br.com.zup.SkillForge.softSkill.dtos.QuestionsRequestDTO;
import br.com.zup.SkillForge.softSkill.dtos.QuestionsResponseDTO;
import br.com.zup.SkillForge.softSkill.services.mappers.QuestionsMapper;
import br.com.zup.SkillForge.softSkill.models.Questions;
import br.com.zup.SkillForge.softSkill.repositories.QuestionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionsService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionsService.class);

    @Autowired
    private QuestionsRepository questionsRepository;

    public QuestionsResponseDTO createQuestion(QuestionsRequestDTO request) {
        logger.info("Creating a new question");
        Questions question = QuestionsMapper.INSTANCE.toModel(request);
        question = questionsRepository.save(question);
        return QuestionsMapper.INSTANCE.toDto(question);
    }

    public QuestionsResponseDTO updateQuestion(Long id, QuestionsRequestDTO updatedRequest) {
        logger.info("Updating question with id: {}", id);
        return questionsRepository.findById(id).map(existingQuestion -> {
            Questions updatedQuestion = QuestionsMapper.INSTANCE.toModel(updatedRequest);
            existingQuestion.setTitle(updatedQuestion.getTitle());
            existingQuestion.setOptionA(updatedQuestion.getOptionA());
            existingQuestion.setOptionB(updatedQuestion.getOptionB());
            existingQuestion.setOptionC(updatedQuestion.getOptionC());
            return QuestionsMapper.INSTANCE.toDto(questionsRepository.save(existingQuestion));
        }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + id));
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
                .map(QuestionsMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public QuestionsResponseDTO getQuestionById(Long id) {
        logger.info("Getting question with id: {}", id);
        return questionsRepository.findById(id)
                .map(QuestionsMapper.INSTANCE::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + id));
    }
}
