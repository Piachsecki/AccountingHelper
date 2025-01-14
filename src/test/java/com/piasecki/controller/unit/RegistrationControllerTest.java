import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.piasecki.controller.RegistrationController;
import com.piasecki.domain.User;
import com.piasecki.service.UserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

class RegistrationControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegistrationController registrationController;

    /*
        wazne aby uzywac z paczki jakarta a nie javax!!! : https://stackoverflow.com/questions/36329001/unable-to-create-a-configuration-because-no-bean-validation-provider-could-be-f

        wazne zeby w metodach, gdzie ta validaja ma byc przerabiana bylo ustawiane:
        @Valid User user
     */

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
    }

    @Test
    void createUser_ShouldEncodePasswordAndSaveUser_WithValidNIP() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("plainPassword");
        user.setEmail("test@example.com");
        user.setNIP("1234567890");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("testUser");
        savedUser.setPassword("encodedPassword");
        savedUser.setEmail("test@example.com");
        savedUser.setNIP("1234567890");

        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");
        when(userService.addUser(user)).thenReturn(savedUser);

        // Act
        ResponseEntity<User> response = registrationController.createUser(user);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(savedUser, response.getBody());

        verify(passwordEncoder, times(1)).encode("plainPassword");
        verify(userService, times(1)).addUser(user);
    }

    @Test
    void createUser_ShouldThrowException_ForInvalidNIP() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("plainPassword");
        user.setEmail("test@example.com");
        user.setNIP("invalidNIP"); // Invalid NIP


        Set<ConstraintViolation<User>> validate = validator.validate(user);
        assertEquals(1, validate.size());

    }

    @Test
    void createUser_ShouldThrowException_ForNullNIP() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("plainPassword");
        user.setEmail("test@example.com");
        user.setNIP(null); // Null NIP

        Set<ConstraintViolation<User>> validate = validator.validate(user);
        assertEquals(1, validate.size());
    }


    @Test
    void createUser_ShouldHandleNullUser() {
        // Arrange
        User user = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> registrationController.createUser(user));
    }
}
