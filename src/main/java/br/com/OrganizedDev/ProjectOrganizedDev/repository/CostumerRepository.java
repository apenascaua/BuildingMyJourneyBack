package br.com.OrganizedDev.ProjectOrganizedDev.repository;

import br.com.OrganizedDev.ProjectOrganizedDev.entity.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CostumerRepository extends JpaRepository<Costumer, Long> {

    Optional<Costumer> findByCnpj(String cnpj);

}
