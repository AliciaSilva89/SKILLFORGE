package br.com.zup.SkillForge.register.controllers;

import br.com.zup.SkillForge.register.dtos.UserRequestDTO;
import br.com.zup.SkillForge.register.dtos.UserResponseDTO;
import br.com.zup.SkillForge.register.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO requestDTO) {
        if (!requestDTO.isPasswordsEqual()) {
            logger.error("Password and confirmation do not match for user with email: {}", requestDTO.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        UserResponseDTO responseDTO = userService.createUser(requestDTO);
        logger.info("User created with email: {}", requestDTO.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UserRequestDTO requestDTO) {
        if (!requestDTO.isPasswordsEqual()) {
            logger.error("Password and confirmation do not match for user with email: {}", requestDTO.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        UserResponseDTO responseDTO = userService.updateUser(id, requestDTO);
        logger.info("User updated with id: {}", id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listUsers() {
        List<UserResponseDTO> responseDTOList = userService.listUsers();
        logger.info("Listing all users");
        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO responseDTO = userService.getUserById(id);
        logger.info("Fetching user with id: {}", id);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        logger.info("User deleted with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}