package br.com.zup.SkillForge.register.services;


import br.com.zup.SkillForge.infras.ResourceNotFoundException;
import br.com.zup.SkillForge.register.models.User;
import br.com.zup.SkillForge.register.dtos.UserRequestDTO;
import br.com.zup.SkillForge.register.dtos.UserResponseDTO;
import br.com.zup.SkillForge.register.repositories.UserRepository;
import br.com.zup.SkillForge.register.services.mappers.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (!userRequestDTO.isPasswordsEqual()) {
            logger.error("Password and confirmation do not match for user with email: {}", userRequestDTO.getEmail());
            throw new IllegalArgumentException("Passwords do not match");
        }

        User user = userMapper.toModel(userRequestDTO);
        user = userRepository.save(user);
        logger.info("User created with id: {}", user.getId());
        return userMapper.toDto(user);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        if (!userRequestDTO.isPasswordsEqual()) {
            logger.error("Password and confirmation do not match for user with email: {}", userRequestDTO.getEmail());
            throw new IllegalArgumentException("Passwords do not match");
        }

        return userRepository.findById(id).map(existingUser -> {
            User updatedUser = userMapper.toModel(userRequestDTO);
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            User savedUser = userRepository.save(existingUser);
            logger.info("User updated with id: {}", savedUser.getId());
            return userMapper.toDto(savedUser);
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

    public List<UserResponseDTO> listUsers() {
        List<UserResponseDTO> users = userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        logger.info("Listing all users");
        return users;
    }

    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> {
                    logger.error("User not found with id: {}", id);
                    return new ResourceNotFoundException("User not found with id " + id);
                });
    }
}