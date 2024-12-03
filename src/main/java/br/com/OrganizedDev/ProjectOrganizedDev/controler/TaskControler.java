package br.com.OrganizedDev.ProjectOrganizedDev.controler;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.TaskDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Project;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Status;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Task;
import br.com.OrganizedDev.ProjectOrganizedDev.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/tasks")

public class TaskControler {

    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public List<Task> getAll(){
        return taskService.getAllTasks();
    }

    @GetMapping("/dto")
    public List<TaskDTO> getAllTasksDTO(){
        return taskService.getAllTasksFromDTO();
    }

    @PostMapping("")
    public Task create(@RequestBody Task task) {
        if (task.getProject() == null || task.getProject().getId() == null) {
            throw new RuntimeException("PROJECT ID must be provided");
        }
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task){
        task.setId(id);
        Optional<Task> updatedTask = taskService.updateTask(id, task);
        return updatedTask.get();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id){
        Task task = taskService.getTaskById(id).get();
        return task;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        taskService.deleteTask(id);
    }
    @GetMapping("/projects/{id}")
    public List<Task> getTaskByProjectId(@PathVariable Long id) {
        return taskService.getTaskByProjectId(id);
    }
    @PatchMapping("/{id}/status")
    public void updateTaskStatus(@PathVariable (value = "id") Long id, @RequestBody Status status) {
        taskService.updateTaskStatus(id, status);
    }
}
