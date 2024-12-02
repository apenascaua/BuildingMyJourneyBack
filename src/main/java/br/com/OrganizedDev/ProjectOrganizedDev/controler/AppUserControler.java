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
        System.out.println("Rodou o Get");
        return appuserService.getAllUsers();
    }

    @GetMapping("/dto")
    public List<AppUserDTO> getAllUserDTO() {
        System.out.println("Rodou o Get");
        return appuserService.getAllUsersFromDTO();
    }

    @PostMapping("")
    public AppUser create(@RequestBody AppUser appuser) {
        System.out.println("Rodou o Post");
        return appuserService.createUser(appuser);
    }

    @PutMapping("/{id}")
    public AppUser update(@PathVariable Long id, @RequestBody AppUser appuser) {
        appuser.setId(id);
        Optional<AppUser> updateAppUser = appuserService.updateUser(id, appuser);
        System.out.println("Rodou o Put");
        return updateAppUser.get();
    }

    @GetMapping("/{id}")
    public AppUser getById(@PathVariable Long id) {
        AppUser appuser = appuserService.getUserById(id).get();
        System.out.println("Rodou o Get");
        return appuser;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        appuserService.deleteUser(id);
        System.out.println("Rodou o Delete");
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
