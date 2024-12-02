package br.com.OrganizedDev.ProjectOrganizedDev.dto.response;

import br.com.OrganizedDev.ProjectOrganizedDev.entity.Status;

import java.time.LocalDate;

public record TaskDTO(
        String nome,
        Status status,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataTermino
) {
}
