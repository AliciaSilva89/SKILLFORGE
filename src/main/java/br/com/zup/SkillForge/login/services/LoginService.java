package br.com.zup.SkillForge.login.services;

import br.com.zup.SkillForge.infras.ResourceNotFoundException;
import br.com.zup.SkillForge.login.dtos.LoginUserRequestDTO;
import br.com.zup.SkillForge.login.dtos.LoginUserResponseDTO;
import br.com.zup.SkillForge.login.models.LoginUser;
import br.com.zup.SkillForge.login.repositories.LoginRepository;
import br.com.zup.SkillForge.login.services.mappers.LoginMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private LoginRepository userRepository;
    private LoginMapper userMapper;

    public LoginService(LoginRepository userRepository, LoginMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public LoginUserResponseDTO createUser(LoginUserRequestDTO loginUserRequestDTO) {
        LoginUser loginUser = userMapper.toModel(loginUserRequestDTO);
        loginUser = userRepository.save(loginUser);
        logger.info("User created with id: {}", loginUser.getId());
        return userMapper.toDto(loginUser);
    }

    public LoginUserResponseDTO updateUser(Long id, LoginUserRequestDTO loginUserRequestDTO) {

        return userRepository.findById(id).map(existingLoginUser -> {
            LoginUser updatedLoginUser = userMapper.toModel(loginUserRequestDTO);
            existingLoginUser.setEmail(updatedLoginUser.getEmail());
            existingLoginUser.setPassword(updatedLoginUser.getPassword());
            LoginUser savedLoginUser = userRepository.save(existingLoginUser);
            logger.info("User updated with id: {}", savedLoginUser.getId());
            return userMapper.toDto(savedLoginUser);
        }).orElseThrow(() -> {
            logger.error("User not found with id: {}", id);
            return new ResourceNotFoundException("User not found with id " + id);
        });
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            logger.error("User not found with id: {}", id);
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
        logger.info("User deleted with id: {}", id);
    }

    public List<LoginUserResponseDTO> listUsers() {
        List<LoginUserResponseDTO> users = userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        logger.info("Listing all users");
        return users;
    }

    public LoginUserResponseDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> {
                    logger.error("User not found with id: {}", id);
                    return new ResourceNotFoundException("User not found with id " + id);
                });
    }
}

