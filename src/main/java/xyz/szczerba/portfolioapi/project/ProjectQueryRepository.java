package xyz.szczerba.portfolioapi.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProjectQueryRepository {

    Project save(Project project);

    Page<Project> findAll(Pageable page);

    Optional<Project> findById(long id);

    void deleteById(long projectId);
}
