package br.com.OrganizedDev.ProjectOrganizedDev.Test;

import br.com.OrganizedDev.ProjectOrganizedDev.entity.Task;
import br.com.OrganizedDev.ProjectOrganizedDev.repository.TaskRepository;
import br.com.OrganizedDev.ProjectOrganizedDev.service.TaskServiceImpl;
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

public class TaskTest {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();
        assertEquals(2, result.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void testGetTaskById() {
        Task task = new Task();
        task.setId(1L);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.getTaskById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateTask() {
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setDescricao("Existing Task");

        Task taskDetails = new Task();
        taskDetails.setDescricao("Updated Task");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(existingTask);

        Optional<Task> result = taskService.updateTask(1L, taskDetails);
        assertTrue(result.isPresent());
        assertEquals("Updated Task", result.get().getDescricao());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(existingTask);
    }

    @Test
    void testDeleteTask() {
        Task task = new Task();
        task.setId(1L);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        doNothing().when(taskRepository).delete(task);

        boolean result = taskService.deleteTask(1L);
        assertTrue(result);
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).delete(task);
    }

    @Test
    void testDeleteTaskNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        boolean result = taskService.deleteTask(1L);
        assertFalse(result);
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, never()).delete(any(Task.class));
    }
}
