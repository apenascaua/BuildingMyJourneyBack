package br.com.OrganizedDev.ProjectOrganizedDev.Test;

import br.com.OrganizedDev.ProjectOrganizedDev.entity.Project;
import br.com.OrganizedDev.ProjectOrganizedDev.repository.ProjectRepository;
import br.com.OrganizedDev.ProjectOrganizedDev.service.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProjectTest {

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Mock
    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        projects.add(new Project());

        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> result = projectService.getAllProjects();
        assertEquals(2, result.size());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void testGetProjectById() {
        Project project = new Project();
        project.setId(1L);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        Optional<Project> result = projectService.getProjectById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(projectRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateProject() {
        Project project = new Project();
        project.setNome("Test Project");

        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project result = projectService.createProject(project);
        assertNotNull(result);
        assertEquals("Test Project", result.getNome());
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testUpdateProject() {
        Project existingProject = new Project();
        existingProject.setId(1L);
        existingProject.setNome("Existing Project");

        Project projectDetails = new Project();
        projectDetails.setNome("Updated Project");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(existingProject));
        when(projectRepository.save(any(Project.class))).thenReturn(existingProject);

        Optional<Project> result = projectService.updateProject(1L, projectDetails);
        assertTrue(result.isPresent());
        assertEquals("Updated Project", result.get().getNome());
        verify(projectRepository, times(1)).findById(1L);
        verify(projectRepository, times(1)).save(existingProject);
    }

    @Test
    void testDeleteProject() {
        Project project = new Project();
        project.setId(1L);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        doNothing().when(projectRepository).delete(project);

        boolean result = projectService.deleteProject(1L);
        assertTrue(result);
        verify(projectRepository, times(1)).findById(1L);
        verify(projectRepository, times(1)).delete(project);
    }

    @Test
    void testDeleteProjectNotFound() {
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        boolean result = projectService.deleteProject(1L);
        assertFalse(result);
        verify(projectRepository, times(1)).findById(1L);
        verify(projectRepository, never()).delete(any(Project.class));
    }
}
