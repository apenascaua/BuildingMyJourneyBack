package br.com.OrganizedDev.ProjectOrganizedDev.Test;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.AppUserDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.AppUser;
import br.com.OrganizedDev.ProjectOrganizedDev.repository.AppUserRepository;
import br.com.OrganizedDev.ProjectOrganizedDev.service.AppUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AppUserTest {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserServiceImpl appUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsersTest() {
        AppUser user1 = new AppUser();
        AppUser user2 = new AppUser();
        List<AppUser> users = Arrays.asList(user1, user2);

        when(appUserRepository.findAll()).thenReturn(users);

        List<AppUserDTO> result = appUserService.getAllUsersFromDTO();

        assertEquals(2, result.size());
        verify(appUserRepository, times(1)).findAll();
    }

    @Test
    void getUserByIdTest() {
        AppUser user = new AppUser();
        user.setId(1L);

        when(appUserRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<AppUser> result = appUserService.getUserById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(appUserRepository, times(1)).findById(1L);
    }

    @Test
    void createUserTest() {
        AppUser user = new AppUser();

        when(appUserRepository.save(user)).thenReturn(user);

        AppUser result = appUserService.createUser(user);

        assertNotNull(result);
        verify(appUserRepository, times(1)).save(user);
    }

    @Test
    void updateUserTest() {
        AppUser existingUser = new AppUser();
        existingUser.setId(1L);
        existingUser.setUserName("oldUser");
        existingUser.setEmail("oldEmail");

        AppUser newUserDetails = new AppUser();
        newUserDetails.setUserName("newUser");
        newUserDetails.setEmail("newEmail");

        when(appUserRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(appUserRepository.save(any(AppUser.class))).thenReturn(existingUser);

        Optional<AppUser> result = appUserService.updateUser(1L, newUserDetails);

        assertTrue(result.isPresent());
        assertEquals("newUser", result.get().getUserName());
        assertEquals("newEmail", result.get().getEmail());
        verify(appUserRepository, times(1)).findById(1L);
        verify(appUserRepository, times(1)).save(existingUser);
    }

    @Test
    void deleteUserTest() {
        AppUser user = new AppUser();
        user.setId(1L);

        when(appUserRepository.findById(1L)).thenReturn(Optional.of(user));

        boolean result = appUserService.deleteUser(1L);

        assertTrue(result);
        verify(appUserRepository, times(1)).findById(1L);
        verify(appUserRepository, times(1)).delete(user);
    }

    @Test
    void deleteUserNotFoundTest() {
        when(appUserRepository.findById(1L)).thenReturn(Optional.empty());

        boolean result = appUserService.deleteUser(1L);

        assertFalse(result);
        verify(appUserRepository, times(1)).findById(1L);
        verify(appUserRepository, never()).delete(any(AppUser.class));
    }
}
