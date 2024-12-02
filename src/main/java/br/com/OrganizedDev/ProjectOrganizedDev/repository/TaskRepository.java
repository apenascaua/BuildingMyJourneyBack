package br.com.OrganizedDev.ProjectOrganizedDev.repository;

import br.com.OrganizedDev.ProjectOrganizedDev.entity.Status;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>{
    List<Task> findByProjectId(Long id);

    @Modifying
    @Query ("UPDATE Task t SET t.status = :status WHERE t.id = :id")
    @Transactional
    public void updateTaskStatus(Long id, Status status);
}
