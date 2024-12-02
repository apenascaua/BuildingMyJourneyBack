package br.com.OrganizedDev.ProjectOrganizedDev.dto.response;

import br.com.OrganizedDev.ProjectOrganizedDev.entity.Project;

import java.time.LocalDate;
import java.util.List;

public record CostumerDTO (
        String nomeFantasia,
        String setorDeAtuacao,
        String descricao,
        String telefone,
        int numeroDeFuncionarios,
        LocalDate dataFundacao
){
}
