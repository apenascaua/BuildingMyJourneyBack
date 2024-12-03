package br.com.OrganizedDev.ProjectOrganizedDev.dto.response;

public record RegisterRequestDTO (
        String name,
        String email,
        String password) {
}
