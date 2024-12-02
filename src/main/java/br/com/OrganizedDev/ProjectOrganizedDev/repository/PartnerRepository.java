package br.com.OrganizedDev.ProjectOrganizedDev.repository;

import br.com.OrganizedDev.ProjectOrganizedDev.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    List<Partner> findByTaskId(Long id);



}
