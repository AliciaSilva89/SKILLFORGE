package br.com.zup.SkillForge.loginTest;

import br.com.zup.SkillForge.infras.security.PasswordUtil;
import br.com.zup.SkillForge.login.dtos.LoginUserRequestDTO;
import br.com.zup.SkillForge.login.repositories.LoginRepository;
import br.com.zup.SkillForge.login.services.LoginService;
import br.com.zup.SkillForge.login.services.mappers.LoginMapper;
import br.com.zup.SkillForge.register.models.RegisterUser;
import br.com.zup.SkillForge.register.repositories.RegisterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import br.com.zup.SkillForge.infras.security.JwtUtil;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginServiceTest {

    @Mock
    private LoginRepository loginRepository;

    @Mock
    private RegisterRepository registerRepository;

    @Mock
    private LoginMapper loginMapper;

    @InjectMocks
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginWithJwtSuccess() {
        String email = "test@example.com";
        String password = "password123";
        String hashedPassword = PasswordUtil.hashPassword(password);
        LoginUserRequestDTO loginUserRequestDTO = new LoginUserRequestDTO(email, password);

        RegisterUser registeredUser = new RegisterUser();
        registeredUser.setEmail(email);
        registeredUser.setPassword(hashedPassword);

        when(registerRepository.findByEmail(email)).thenReturn(Optional.of(registeredUser));

        String token = loginService.loginWithToken(loginUserRequestDTO);

        assertNotNull(token);
        assertTrue(JwtUtil.validateToken(token, email));
        verify(registerRepository, times(1)).findByEmail(email);
    }

    @Test
    public void testLoginWithJwtInvalidPassword() {
        String email = "test@example.com";
        String password = "wrongpassword";
        String hashedPassword = PasswordUtil.hashPassword("correctpassword");
        LoginUserRequestDTO loginUserRequestDTO = new LoginUserRequestDTO(email, password);

        RegisterUser registeredUser = new RegisterUser();
        registeredUser.setEmail(email);
        registeredUser.setPassword(hashedPassword);

        when(registerRepository.findByEmail(email)).thenReturn(Optional.of(registeredUser));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            loginService.loginWithToken(loginUserRequestDTO);
        });

        assertEquals("Invalid email or password", exception.getMessage());
        verify(registerRepository, times(1)).findByEmail(email);
    }
}