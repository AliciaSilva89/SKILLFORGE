package br.com.zup.SkillForge.login.services;

import br.com.zup.SkillForge.infras.ResourceNotFoundException;
import br.com.zup.SkillForge.login.dtos.LoginUserRequestDTO;
import br.com.zup.SkillForge.login.dtos.LoginUserResponseDTO;
import br.com.zup.SkillForge.login.models.LoginUser;
import br.com.zup.SkillForge.login.repositories.LoginRepository;
import br.com.zup.SkillForge.login.services.mappers.LoginMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private final LoginRepository loginRepository;
    private final LoginMapper loginMapper;

    public LoginService(LoginRepository loginRepository, LoginMapper loginMapper) {
        this.loginRepository = loginRepository;
        this.loginMapper = loginMapper;
    }


    public LoginUserResponseDTO createUser(LoginUserRequestDTO loginUserRequestDTO) {
        LoginUser loginUser = loginMapper.toModel(loginUserRequestDTO);
        loginUser = loginRepository.save(loginUser);
        logger.info("User created with id: {}", loginUser.getId());
        return loginMapper.toDto(loginUser);
    }

    public LoginUserResponseDTO updateUser(Long id, LoginUserRequestDTO loginUserRequestDTO) {
        return loginRepository.findById(id)
                .map(existingLoginUser -> {
                    existingLoginUser.setEmail(loginUserRequestDTO.getEmail());
                    existingLoginUser.setPassword(loginUserRequestDTO.getPassword());
                    LoginUser savedLoginUser = loginRepository.save(existingLoginUser);
                    logger.info("User updated with id: {}", savedLoginUser.getId());
                    return loginMapper.toDto(savedLoginUser);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    public void deleteUser(Long id) {
        if (!loginRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        loginRepository.deleteById(id);
        logger.info("User deleted with id: {}", id);
    }

    public List<LoginUserResponseDTO> listUsers() {
        List<LoginUserResponseDTO> users = loginRepository.findAll().stream()
                .map(loginMapper::toDto)
                .collect(Collectors.toList());
        logger.info("Listing all users");
        return users;
    }

    public LoginUserResponseDTO getUserById(Long id) {
        return loginRepository.findById(id)
                .map(loginMapper::toDto)
                .orElseThrow(() -> {
                    logger.error("User not found with id: {}", id);
                    return new ResourceNotFoundException("User not found with id " + id);
                });
    }
}
