package br.com.OrganizedDev.ProjectOrganizedDev.repository;

import br.com.OrganizedDev.ProjectOrganizedDev.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {


    @Query("SELECT a FROM Project a JOIN a.costumer p WHERE p.id = :id")
    List<Project> findByCostumerId(@Param("id") Long id);

}
