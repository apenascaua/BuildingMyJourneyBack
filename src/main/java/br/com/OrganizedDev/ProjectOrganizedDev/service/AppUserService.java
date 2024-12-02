package br.com.OrganizedDev.ProjectOrganizedDev.service;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.AppUserDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {
    List<AppUser> getAllUsers();
    List<AppUserDTO> getAllUsersFromDTO();
    Optional<AppUser> getUserById(Long id);
    AppUser createUser(AppUser appUser);
    Optional<AppUser> updateUser(Long id, AppUser userDetails);
    boolean deleteUser(Long id);
    Optional<AppUser> findByEmail(String email);
}
