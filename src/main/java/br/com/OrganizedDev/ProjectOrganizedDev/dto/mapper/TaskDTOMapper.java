package br.com.OrganizedDev.ProjectOrganizedDev.dto.mapper;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.TaskDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Task;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TaskDTOMapper implements Function<Task, TaskDTO> {
    @Override
    public TaskDTO apply(Task task) {
        return new TaskDTO(
                task.getNome(),
                task.getStatus(),
                task.getDescricao(),
                task.getDataInicio(),
                task.getDataTermino()
        );
    }
}
