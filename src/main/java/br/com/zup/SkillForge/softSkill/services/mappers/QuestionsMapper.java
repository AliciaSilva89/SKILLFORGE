package br.com.zup.SkillForge.softSkill.services.mappers;

import br.com.zup.SkillForge.softSkill.dtos.QuestionsRequestDTO;
import br.com.zup.SkillForge.softSkill.dtos.QuestionsResponseDTO;
import br.com.zup.SkillForge.softSkill.models.Questions;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuestionsMapper {
    QuestionsMapper INSTANCE = Mappers.getMapper(QuestionsMapper.class);

    Questions toModel(QuestionsRequestDTO dto);

    QuestionsResponseDTO toDto(Questions questions);
}
