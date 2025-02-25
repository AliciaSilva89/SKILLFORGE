package br.com.zup.SkillForge.login.services.mappers;

import br.com.zup.SkillForge.login.dtos.UserRequestDTO;
import br.com.zup.SkillForge.login.dtos.UserResponseDTO;
import br.com.zup.SkillForge.login.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoginMapper {
    br.com.zup.SkillForge.register.services.mappers.UserMapper INSTANCE = Mappers.getMapper(br.com.zup.SkillForge.register.services.mappers.UserMapper.class);

    User toModel(UserRequestDTO dto);

    UserResponseDTO toDto(User user);
}