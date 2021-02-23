package xyz.szczerba.portfolioapi.project;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import xyz.szczerba.portfolioapi.project.dto.ProjectReadModel;
import xyz.szczerba.portfolioapi.project.dto.ProjectWriteModel;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
class ProjectController {
    private ProjectService service;

    ProjectController(final ProjectService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProjectReadModel>> readAll(
            @RequestParam(required = false) Integer page,
            Sort.Direction sort,
            String sortBy,
            @RequestParam(required = false) Integer number
    ) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";
        int numberOfPosts = number != null && number >= 0 ? number: 25;

        List<Project> projects = service.getAll(pageNumber, sortDirection, sortByVariable, numberOfPosts);

        return ResponseEntity.ok(ProjectFactory.toDto(projects));
    }

    @PostMapping
    public ResponseEntity<ProjectReadModel> create(
            @RequestBody @Valid ProjectWriteModel project,
            Authentication authentication
    ) throws IllegalAccessException {
        if(authentication == null) {
            throw new IllegalAccessException("You must be logged in first");
        }
        Project result = service.save(ProjectFactory.toEntity(project));
        return ResponseEntity.created(URI.create("/" + result.getId())).body(ProjectFactory.toDto(result));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ProjectReadModel> update(
            @PathVariable(name = "id") long projectId,
            @RequestBody @Valid ProjectWriteModel project,
            Authentication authentication
    ) throws IllegalAccessException {
        if(authentication == null) {
            throw new IllegalAccessException("You must be logged in first");
        }
        Project result = service.modify(projectId, ProjectFactory.toEntity(project));
        return ResponseEntity.created(URI.create("/" + result.getId())).body(ProjectFactory.toDto(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") long projectId) {
        service.delete(projectId);
        return ResponseEntity.noContent().build();
    }
}
