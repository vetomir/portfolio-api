package xyz.szczerba.portfolioapi.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xyz.szczerba.portfolioapi.project.dto.ProjectReadModel;
import xyz.szczerba.portfolioapi.project.dto.ProjectWriteModel;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class ProjectService{
    private final Logger logger = LoggerFactory.getLogger(ProjectService.class);
    private final ProjectQueryRepository projectQueryRepository;
    private final TagQueryRepository tagQueryRepository;

    ProjectService(final ProjectQueryRepository projectQueryRepository, final TagQueryRepository tagQueryRepository) {
        this.projectQueryRepository = projectQueryRepository;
        this.tagQueryRepository = tagQueryRepository;
    }

    public List<ProjectReadModel> getAll(int page, Sort.Direction sort, String sortBy, int items) {
        List<Project> list = projectQueryRepository.findAll(
                PageRequest.of(page, items,
                        Sort.by(sort, sortBy)
                )).getContent();

        return ProjectFactory.toDto(list);
    }

    public ProjectReadModel getSingle(long id){
        return ProjectFactory.toDto(projectQueryRepository.findById(id).get());
    }

    public ProjectReadModel save(ProjectWriteModel source){
        Project toSave = ProjectFactory.toEntity(source);

        toSave.setTags(saveTags(toSave.getTags()));

        Project result = projectQueryRepository.save(toSave);
        return ProjectFactory.toDto(result);
    }
    public Set<Tag> saveTags(Set<Tag> tags){
        Set<Tag> tagSet = tags.stream().map(x -> {
            Optional<Tag> tag = tagQueryRepository.getByName(x.getName());

            if (tag.isEmpty()) {
                return tagQueryRepository.save(x);
            } else {
                return tag.get();
            }
        }).collect(Collectors.toSet());

        return tagSet;
    }

    @DeclutterTags
    public ProjectReadModel modify(long id, ProjectWriteModel source){
        Project result = ProjectFactory.toEntity(source);
        result.setId(id);

        result.setTags(saveTags(result.getTags()));

        return ProjectFactory.toDto(projectQueryRepository.save(result));
    }

    @DeclutterTags
    public void delete(long id){
        projectQueryRepository.deleteById(id);
    }
}
