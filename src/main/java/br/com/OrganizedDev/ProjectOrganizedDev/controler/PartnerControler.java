package br.com.OrganizedDev.ProjectOrganizedDev.controler;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.PartnerDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Partner;
import br.com.OrganizedDev.ProjectOrganizedDev.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/partners")
public class PartnerControler {

    @Autowired
    private PartnerService partnerService;

    @GetMapping("")
    public List<Partner> getAllPartners() {
        return partnerService.getAllPartners();
    }

    @GetMapping("/dto")
    public List<PartnerDTO> getAllPartnersDTO(){
        System.out.println("Rodou o Get");
        return partnerService.getAllPartnersFromDTO();
    }

    @PostMapping("")
    public Partner create(@RequestBody Partner partner){
        System.out.println("Rodou o Post");
        return partnerService.createPartner(partner);
    }

    @PutMapping("/{id}")
    public Partner update(@PathVariable Long id, @RequestBody Partner partner){
        partner.setId(id);
        Optional<Partner> updatedPartner = partnerService.updatePartner(id, partner);
        System.out.println("Rodou o Put");
        return updatedPartner.get();
    }

    @GetMapping("/{id}")
    public Partner getById(@PathVariable Long id){
        Partner partner = partnerService.getPartnerById(id).get();
        System.out.println("Rodou o Get");
        return partner;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        partnerService.deletePartner(id);
        System.out.println("Rodou o Delete");
    }
    @GetMapping("/tasks/{id}")
    public List<Partner> getPartnerByTaskId(@PathVariable Long id) {
        return partnerService.getPartnerByTaskId(id);
    }
}
