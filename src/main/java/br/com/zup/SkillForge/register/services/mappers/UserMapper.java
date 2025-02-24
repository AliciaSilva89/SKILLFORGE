package br.com.zup.SkillForge.register.services.mappers;

import br.com.zup.SkillForge.register.dtos.UserRequestDTO;
import br.com.zup.SkillForge.register.dtos.UserResponseDTO;
import br.com.zup.SkillForge.register.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toModel(UserRequestDTO dto);

    UserResponseDTO toDto(User user);
}