package br.com.OrganizedDev.ProjectOrganizedDev.service;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.TaskDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Status;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> getAllTasks();
    List<TaskDTO>getAllTasksFromDTO();
    Optional<Task> getTaskById(Long id);
    Task createTask(Task task);
    Optional<Task> updateTask(Long id, Task taskDetails);
    boolean deleteTask(Long id);
    List<Task> getTaskByProjectId(Long id);
    void updateTaskStatus(Long id, Status status);
}
