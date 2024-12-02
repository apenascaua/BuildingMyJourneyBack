package br.com.OrganizedDev.ProjectOrganizedDev.dto.mapper;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.AppUserDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.CostumerDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Costumer;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class  CostumerDTOMapper implements Function<Costumer, CostumerDTO> {


    @Override
    public CostumerDTO apply(Costumer costumer) {
        return new CostumerDTO(
                costumer.getNomeFantasia(),
                costumer.getSetorDeAtuacao(),
                costumer.getDescricao(),
                costumer.getTelefone(),
                costumer.getNumeroDeFuncionarios(),
                costumer.getDataFundacao()
        );
    }
}
