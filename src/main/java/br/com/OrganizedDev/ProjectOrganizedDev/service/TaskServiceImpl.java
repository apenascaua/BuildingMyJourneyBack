package br.com.OrganizedDev.ProjectOrganizedDev.service;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.mapper.TaskDTOMapper;
import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.TaskDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Costumer;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Project;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Status;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Task;
import br.com.OrganizedDev.ProjectOrganizedDev.repository.ProjectRepository;
import br.com.OrganizedDev.ProjectOrganizedDev.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskDTOMapper taskDTOMapper;

    @Override
    public List<Task> getAllTasks() {

        return taskRepository.findAll();
    }

    @Override
    public List<TaskDTO>getAllTasksFromDTO() {
        return Optional.of(taskRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream().map(taskDTOMapper).toList())
                .orElseThrow(() -> new RuntimeException("NO USERS FOUND"));

    }

    @Override
    public Optional<Task> getTaskById(Long id) {

        return taskRepository.findById(id);
    }

    @Override
    @Transactional
    public Task createTask(Task task) {
        if (task.getProject() == null || task.getProject().getId() == null) {
            throw new RuntimeException("Costumer ID must be provided");
        }
        Optional<Project> optionalProject = projectRepository.findById(task.getProject().getId());
        if (optionalProject.isPresent()) {
            task.setProject(optionalProject.get());
            return taskRepository.save(task);
        } else {
            throw new RuntimeException("Project not found");
        }
    }

    @Override
    @Transactional
    public Optional<Task> updateTask(Long id, Task taskDetails) {
        return taskRepository.findById(id).map(task -> {
            task.setStatus(taskDetails.getStatus() ) ;
            task.setNome(taskDetails.getNome() ); ;
            task.setDescricao(taskDetails.getDescricao());
            task.setDataInicio(taskDetails.getDataInicio());
            task.setDataTermino(taskDetails.getDataTermino());
            return taskRepository.save(task);
        });
    }

    @Override
    @Transactional
    public boolean deleteTask(Long id) {
        return taskRepository.findById(id).map(task -> {
            taskRepository.delete(task);
            return true;
        }).orElse(false);
    }

    @Override
    public List<Task> getTaskByProjectId(Long id) {
        return taskRepository.findByProjectId(id);
    }
    @Override
    public void updateTaskStatus(Long id, Status status) {
        taskRepository.updateTaskStatus(id, status);
    }
}
