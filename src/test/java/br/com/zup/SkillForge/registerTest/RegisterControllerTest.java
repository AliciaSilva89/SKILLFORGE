package br.com.zup.SkillForge.registerTest;

import br.com.zup.SkillForge.register.controllers.RegisterController;
import br.com.zup.SkillForge.register.dtos.UserRequestDTO;
import br.com.zup.SkillForge.register.dtos.UserResponseDTO;
import br.com.zup.SkillForge.register.services.RegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RegisterControllerTest {

    @InjectMocks
    private RegisterController userController;

    @Mock
    private RegisterService registerService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateUser() throws Exception {
        UserRequestDTO requestDTO = new UserRequestDTO("user@example.com", "password123", "password123");
        UserResponseDTO responseDTO = new UserResponseDTO(1L, "user@example.com");

        when(registerService.createUser(any(UserRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(responseDTO.getId()))
                .andExpect(jsonPath("$.email").value(responseDTO.getEmail()));
    }

    @Test
    void testUpdateUser() throws Exception {
        Long userId = 1L;
        UserRequestDTO requestDTO = new UserRequestDTO("user@example.com", "newPassword123", "newPassword123");
        UserResponseDTO responseDTO = new UserResponseDTO(userId, "user@example.com");

        when(registerService.updateUser(eq(userId), any(UserRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(responseDTO.getId()))
                .andExpect(jsonPath("$.email").value(responseDTO.getEmail()));
    }

    @Test
    void testGetUserById() throws Exception {
        Long userId = 1L;
        UserResponseDTO responseDTO = new UserResponseDTO(userId, "user@example.com");

        when(registerService.getUserById(eq(userId))).thenReturn(responseDTO);

        mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(responseDTO.getId()))
                .andExpect(jsonPath("$.email").value(responseDTO.getEmail()));
    }

    @Test
    void testListUsers() throws Exception {
        UserResponseDTO user1 = new UserResponseDTO(1L, "user1@example.com");
        UserResponseDTO user2 = new UserResponseDTO(2L, "user2@example.com");
        List<UserResponseDTO> userList = List.of(user1, user2);

        when(registerService.listUsers()).thenReturn(userList);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(user1.getId()))
                .andExpect(jsonPath("$[0].email").value(user1.getEmail()))
                .andExpect(jsonPath("$[1].id").value(user2.getId()))
                .andExpect(jsonPath("$[1].email").value(user2.getEmail()));
    }

    @Test
    void testDeleteUser() throws Exception {
        Long userId = 1L;

        doNothing().when(registerService).deleteUser(eq(userId));

        mockMvc.perform(delete("/users/{id}", userId))
                .andExpect(status().isNoContent());

        verify(registerService, times(1)).deleteUser(eq(userId));
    }
}