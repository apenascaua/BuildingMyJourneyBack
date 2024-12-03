package br.com.OrganizedDev.ProjectOrganizedDev.controler;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.ProjectDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Project;
import br.com.OrganizedDev.ProjectOrganizedDev.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectControler {

    @Autowired
    private ProjectService projectService;

    @GetMapping("")
    public List<Project> getAll() {
        return projectService.getAllProjects();
    }

    @GetMapping("/dto")
    public List<ProjectDTO> getAllProjectDTO() {
        return projectService.getAllProjectsFromDTO();
    }

    @PostMapping("")
    public Project create(@RequestBody Project project) {
        if (project.getCostumer() == null || project.getCostumer().getId() == null) {
            throw new RuntimeException("Costumer ID must be provided");
        }
        return projectService.createProject(project);
    }

    @PutMapping("/{id}")
    public Project update(@PathVariable Long id, @RequestBody Project project) {
        project.setId(id);
        Optional<Project> updatedProject = projectService.updateProject(id, project);
        return updatedProject.orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @GetMapping("/{id}")
    public Project getById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        return project;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        boolean deleted = projectService.deleteProject(id);
        if (!deleted) {
            throw new RuntimeException("Project not found");
        }
    }
    @GetMapping("/costumers/{id}")
    public List<Project> getProjectsByCostumerId(@PathVariable Long id) {
        List<Project> list = projectService.getProjectsByCostumerId(id);

        return list;
    }
}
