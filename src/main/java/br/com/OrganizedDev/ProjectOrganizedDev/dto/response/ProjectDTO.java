package br.com.OrganizedDev.ProjectOrganizedDev.dto.response;

import java.time.LocalDate;

public record ProjectDTO(
        String nome,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataTermino
) {
}
