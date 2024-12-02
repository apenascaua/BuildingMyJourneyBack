package br.com.OrganizedDev.ProjectOrganizedDev.service;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.CostumerDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Costumer;

import java.util.List;
import java.util.Optional;

public interface CostumerService  {
    List<Costumer> getAllCostumers();
    List<CostumerDTO> getAllCostumersFromDTO();
    Optional<Costumer> getCostumerById(Long id);
    Optional<Costumer> getCostumerByCnpj(String cnpj);
    Costumer createCostumer(Costumer costumer);
    Optional<Costumer> updateCostumer(Long id, Costumer costumerDetails);
    boolean deleteCostumer(Long id);

}
