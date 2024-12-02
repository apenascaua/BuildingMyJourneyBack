package br.com.OrganizedDev.ProjectOrganizedDev.service;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.ProjectDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> getAllProjects();
    List<ProjectDTO> getAllProjectsFromDTO();
    Optional<Project> getProjectById(Long id);
    Project createProject(Project project);
    Optional<Project> updateProject(Long id, Project projectDetails);
    boolean deleteProject(Long id);
    List<Project> getProjectsByCostumerId(Long costumerId);
}
