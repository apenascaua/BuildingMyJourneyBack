package br.com.OrganizedDev.ProjectOrganizedDev.service;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.mapper.ProjectDTOMapper;
import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.ProjectDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Costumer;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Project;
import br.com.OrganizedDev.ProjectOrganizedDev.repository.CostumerRepository;
import br.com.OrganizedDev.ProjectOrganizedDev.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private ProjectDTOMapper projectDTOMapper;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<ProjectDTO> getAllProjectsFromDTO() {
        return Optional.of(projectRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream().map(projectDTOMapper).toList())
                .orElseThrow(() -> new RuntimeException("NO USERS FOUND"));

    }

    @Override
    public Optional<Project> getProjectById(Long id) {

        return projectRepository.findById(id);
    }

    public Project createProject(Project project) {
        // Verifica se o costumerId está presente no projeto
        if (project.getCostumer() == null || project.getCostumer().getId() == null) {
            throw new RuntimeException("Costumer ID must be provided");
        }
        // Verifica se o costumer existe
        Optional<Costumer> optionalCostumer = costumerRepository.findById(project.getCostumer().getId());
        if (optionalCostumer.isPresent()) {
            project.setCostumer(optionalCostumer.get());
            return projectRepository.save(project);
        } else {
            throw new RuntimeException("Costumer not found");
        }
    }

    @Override
    @Transactional
    public Optional<Project> updateProject(Long id, Project projectDetails) {
        return projectRepository.findById(id).map(project -> {
            project.setNome(projectDetails.getNome());
            project.setDescricao(projectDetails.getDescricao());
            project.setDataInicio(projectDetails.getDataInicio());
            project.setDataTermino(projectDetails.getDataTermino());
            return projectRepository.save(project);
        });
    }

    @Override
    @Transactional
    public boolean deleteProject(Long id) {
        return projectRepository.findById(id).map(project -> {
            projectRepository.delete(project);
            return true;
        }).orElse(false);
    }
    @Override
    public List<Project> getProjectsByCostumerId(Long id) {
        return projectRepository.findByCostumerId(id);
    }
}
