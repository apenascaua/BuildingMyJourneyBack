package br.com.OrganizedDev.ProjectOrganizedDev.dto.mapper;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.ProjectDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Project;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProjectDTOMapper implements Function<Project, ProjectDTO> {

    @Override
    public ProjectDTO apply(Project project) {
        return new ProjectDTO(
                project.getNome(),
                project.getDescricao(),
                project.getDataInicio(),
                project.getDataTermino()
        );
    }
}
