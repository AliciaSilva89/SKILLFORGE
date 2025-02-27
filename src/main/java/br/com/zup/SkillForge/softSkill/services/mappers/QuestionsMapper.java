package br.com.zup.SkillForge.softSkill.services.mappers;

import br.com.zup.SkillForge.softSkill.dtos.QuestionsRequestDTO;
import br.com.zup.SkillForge.softSkill.dtos.QuestionsResponseDTO;
import br.com.zup.SkillForge.softSkill.models.Questions;
import org.springframework.stereotype.Component;

@Component
public class QuestionsMapper {

    public Questions toModel(QuestionsRequestDTO dto) {
        Questions questions = new Questions();
        questions.setTitle(dto.getTitle());
        questions.setOptionA(dto.getOptionA());
        questions.setOptionB(dto.getOptionB());
        questions.setOptionC(dto.getOptionC());
        return questions;
    }

    public QuestionsResponseDTO toDto(Questions questions) {
        QuestionsResponseDTO dto = new QuestionsResponseDTO();
        dto.setId(questions.getId());
        dto.setTitle(questions.getTitle());
        dto.setOptionA(questions.getOptionA());
        dto.setOptionB(questions.getOptionB());
        dto.setOptionC(questions.getOptionC());
        return dto;
    }
}