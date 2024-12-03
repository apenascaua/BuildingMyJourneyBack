package br.com.OrganizedDev.ProjectOrganizedDev.dto.mapper;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.AppUserDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.AppUser;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class  AppUserDTOMapper implements Function<AppUser, AppUserDTO> {

    @Override
    public AppUserDTO apply(AppUser appUser) {
        return new AppUserDTO(
                appUser.getUserName(),
                appUser.getEmail(),
                appUser.getPassword()
        );
    }
}
