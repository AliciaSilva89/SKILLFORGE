package br.com.zup.SkillForge.login.services.mappers;

import br.com.zup.SkillForge.login.dtos.LoginUserRequestDTO;
import br.com.zup.SkillForge.login.dtos.LoginUserResponseDTO;
import br.com.zup.SkillForge.login.models.LoginUser;
import br.com.zup.SkillForge.register.services.mappers.RegisterMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoginMapper {
    RegisterMapper INSTANCE = Mappers.getMapper(RegisterMapper.class);

    LoginUser toModel(LoginUserRequestDTO dto);

    LoginUserResponseDTO toDto(LoginUser loginUser);
}