package br.com.OrganizedDev.ProjectOrganizedDev.controler;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.AppUserDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.AppUser;
import br.com.OrganizedDev.ProjectOrganizedDev.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class AppUserControler {

    @Autowired
    private AppUserService appuserService;

    @GetMapping("")
    public List<AppUser> getAll() {
        return appuserService.getAllUsers();
    }

    @GetMapping("/dto")
    public List<AppUserDTO> getAllUserDTO() {
        return appuserService.getAllUsersFromDTO();
    }

    @PostMapping("")
    public AppUser create(@RequestBody AppUser appuser) {
        return appuserService.createUser(appuser);
    }

    @PutMapping("/{id}")
    public AppUser update(@PathVariable Long id, @RequestBody AppUser appuser) {
        appuser.setId(id);
        Optional<AppUser> updateAppUser = appuserService.updateUser(id, appuser);
        return updateAppUser.get();
    }

    @GetMapping("/{id}")
    public AppUser getById(@PathVariable Long id) {
        AppUser appuser = appuserService.getUserById(id).get();
        return appuser;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        appuserService.deleteUser(id);
    }

    @GetMapping("/email")
    public ResponseEntity<AppUser> getUserByEmail(@RequestParam String email) {
        AppUser appUser = appuserService.findByEmail(email).orElse(null);
        if (appUser != null) {
            return ResponseEntity.ok(appUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
