package br.com.OrganizedDev.ProjectOrganizedDev.controler;


import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.CostumerDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Costumer;
import br.com.OrganizedDev.ProjectOrganizedDev.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/costumers")
public class CostumerControler {

    @Autowired
    private CostumerService costumerService;

    @GetMapping("")
    public List<Costumer> getAll(){
        return costumerService.getAllCostumers();
    }

    @GetMapping("/dto")
    public List<CostumerDTO> getAllCostumerFromDTO(){
        return costumerService.getAllCostumersFromDTO();
    }

    @GetMapping("/cnpj")
    public ResponseEntity<Costumer> getByCnpj(@RequestParam String cnpj) {
        Optional<Costumer> costumer = costumerService.getCostumerByCnpj(cnpj);
        if (costumer.isPresent()) {
            return ResponseEntity.ok(costumer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("")
    public Costumer create(@RequestBody Costumer costumer){
        return costumerService.createCostumer(costumer);
    }

    @PutMapping("/{id}")
    public Costumer update(@PathVariable Long id, @RequestBody Costumer costumer) {
        costumer.setId(id);
        Optional<Costumer> updateCostumer = costumerService.updateCostumer(id, costumer);
        return updateCostumer.get();
    }

    @GetMapping("/{id}")
    public Costumer getById(@PathVariable Long id){
        Costumer costumer = costumerService.getCostumerById(id).get();
        return costumer;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        costumerService.deleteCostumer(id);
    }
}
