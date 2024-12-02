package br.com.OrganizedDev.ProjectOrganizedDev.service;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.AppUserDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.dto.mapper.AppUserDTOMapper;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.AppUser;
import br.com.OrganizedDev.ProjectOrganizedDev.repository.AppUserRepository;
import br.com.OrganizedDev.ProjectOrganizedDev.repository.CostumerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private AppUserDTOMapper appUserDTOMapper;

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public List<AppUserDTO> getAllUsersFromDTO() {
        return Optional.of(appUserRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream().map(appUserDTOMapper).toList())
                .orElseThrow(() -> new RuntimeException("NO USERS FOUND"));
    }

    @Override
    public Optional<AppUser> getUserById(Long id) {
        return appUserRepository.findById(id);
    }

    @Override
    @Transactional
    public AppUser createUser(AppUser appUser) {
        if (appUser.getCostumer() != null) {
            appUser.getCostumer().setAppUser(appUser);
        }
        return appUserRepository.save(appUser);
    }

    @Override
    @Transactional
    public Optional<AppUser> updateUser(Long id, AppUser userDetails) {
        return appUserRepository.findById(id).map(user -> {
            user.setUserName(userDetails.getUserName());
            user.setPassword(userDetails.getPassword());
            user.setEmail(userDetails.getEmail());
            return appUserRepository.save(user);
        });
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
            appUserRepository.deleteById(id);
            return true;
    }

    @Override
    public Optional<AppUser> findByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }
}
