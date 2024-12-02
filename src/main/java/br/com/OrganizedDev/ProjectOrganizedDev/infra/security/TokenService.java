package br.com.OrganizedDev.ProjectOrganizedDev.infra.security;

import br.com.OrganizedDev.ProjectOrganizedDev.entity.AppUser;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String generateToken(AppUser appUser){
        try{

        }catch (JWTCreationException exception){
            throw new RuntimeException("Error while authentcating");
        }
        return "";
    }
}
