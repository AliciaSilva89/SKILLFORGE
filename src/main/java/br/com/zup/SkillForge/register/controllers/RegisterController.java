package br.com.zup.SkillForge.register.controllers;

import br.com.zup.SkillForge.register.dtos.UserRequestDTO;
import br.com.zup.SkillForge.register.dtos.UserResponseDTO;
import br.com.zup.SkillForge.register.services.RegisterService;
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
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private RegisterService registerService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO requestDTO) {
        if (!requestDTO.isPasswordsEqual()) {
            logger.error("Password and confirmation do not match for user with email: {}", requestDTO.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        UserResponseDTO responseDTO = registerService.createUser(requestDTO);
        logger.info("User created with email: {}", requestDTO.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UserRequestDTO requestDTO) {
        if (!requestDTO.isPasswordsEqual()) {
            logger.error("Password and confirmation do not match for user with email: {}", requestDTO.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        UserResponseDTO responseDTO = registerService.updateUser(id, requestDTO);
        logger.info("User updated with id: {}", id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listUsers() {
        List<UserResponseDTO> responseDTOList = registerService.listUsers();
        logger.info("Listing all users");
        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO responseDTO = registerService.getUserById(id);
        logger.info("Fetching user with id: {}", id);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        registerService.deleteUser(id);
        logger.info("User deleted with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}