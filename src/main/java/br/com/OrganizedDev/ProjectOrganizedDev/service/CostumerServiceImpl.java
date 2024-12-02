package br.com.OrganizedDev.ProjectOrganizedDev.service;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.mapper.CostumerDTOMapper;
import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.CostumerDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.AppUser;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Costumer;
import br.com.OrganizedDev.ProjectOrganizedDev.repository.CostumerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostumerServiceImpl implements CostumerService {

    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private CostumerDTOMapper costumerDTOMapper;

    @Override
    public List<Costumer> getAllCostumers() {
        return costumerRepository.findAll();
    }

    @Override
    public List<CostumerDTO> getAllCostumersFromDTO() {
        return Optional.of(costumerRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream().map(costumerDTOMapper).toList())
                .orElseThrow(() -> new RuntimeException("NO USERS FOUND"));
    }

    @Override
    public Optional<Costumer> getCostumerById(Long id) {

        return costumerRepository.findById(id);
    }

    @Override
    public Optional<Costumer> getCostumerByCnpj(String cnpj) {
        return costumerRepository.findByCnpj(cnpj);
    }

    @Override
    @Transactional
    public Costumer createCostumer(Costumer costumer) {
        AppUser appUser = costumer.getAppUser();
        if (appUser != null) {
            appUser.setCostumer(costumer);
        }
        return costumerRepository.save(costumer);
    }

    @Override
    @Transactional
    public Optional<Costumer> updateCostumer(Long id, Costumer costumerDetails) {
        return costumerRepository.findById(id).map(costumer -> {
            costumer.setCnpj(costumerDetails.getCnpj());
            costumer.setNomeFantasia(costumerDetails.getNomeFantasia());
            costumer.setDescricao(costumerDetails.getDescricao());
            costumer.setDataFundacao(costumerDetails.getDataFundacao());
            costumer.setSetorDeAtuacao(costumerDetails.getSetorDeAtuacao());
            costumer.setNumeroDeFuncionarios(costumerDetails.getNumeroDeFuncionarios());
            costumer.setTelefone(costumerDetails.getTelefone());
            return costumerRepository.save(costumer);
        });
    }

    @Override
    @Transactional
    public boolean deleteCostumer(Long id) {
            costumerRepository.deleteById(id);
            return true;
    }

}
