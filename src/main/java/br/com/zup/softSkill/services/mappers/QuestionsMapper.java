package br.com.zup.softSkill.services.mappers;

import br.com.zup.softSkill.dtos.QuestionsRequestDTO;
import br.com.zup.softSkill.dtos.QuestionsResponseDTO;
import br.com.zup.softSkill.models.Questions;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuestionsMapper {
    QuestionsMapper INSTANCE = Mappers.getMapper(QuestionsMapper.class);

    Questions toModel(QuestionsRequestDTO dto);

    QuestionsResponseDTO toDto(Questions questions);
}
