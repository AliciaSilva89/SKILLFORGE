package br.com.zup.softSkill.services;

import br.com.zup.softSkill.models.Questions;
import br.com.zup.softSkill.repositories.QuestionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionsService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionsService.class);

    @Autowired
    private QuestionsRepository questionsRepository;

    public Questions createQuestion(Questions question) {
        logger.info("Creating a new question");
        return questionsRepository.save(question);
    }

    public Questions updateQuestion(UUID id, Questions updatedQuestion) {
        logger.info("Updating question with id: {}", id);
        return questionsRepository.findById(id).map(existingQuestion -> {
            existingQuestion.setTitle(updatedQuestion.getTitle());
            existingQuestion.setOptionA(updatedQuestion.getOptionA());
            existingQuestion.setOptionB(updatedQuestion.getOptionB());
            existingQuestion.setOptionC(updatedQuestion.getOptionC());
            return questionsRepository.save(existingQuestion);
        }).orElse(null);
    }

    public void deleteQuestion(UUID id) {
        logger.info("Deleting question with id: {}", id);
        questionsRepository.deleteById(id);
    }

    public List<Questions> listQuestions() {
        logger.info("Listing all questions");
        return questionsRepository.findAll();
    }

    public Questions getQuestionById(UUID id) {
        logger.info("Getting question with id: {}", id);
        return questionsRepository.findById(id).orElse(null);
    }
}
