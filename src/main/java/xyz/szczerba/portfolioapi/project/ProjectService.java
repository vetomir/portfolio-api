package xyz.szczerba.portfolioapi.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public List<Project> getAll(int page, Sort.Direction sort, String sortBy, int items) {
        List<Project> list = projectQueryRepository.findAll(
                PageRequest.of(page, items,
                        Sort.by(sort, sortBy)
                )).getContent();

        return list;
    }

    public Project getSingle(long id){
        return projectQueryRepository.findById(id).get();
    }

    public Project save(Project source){

        source.setTags(saveTags(source.getTags()));

        Project result = projectQueryRepository.save(source);
        return result;
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
    public Project modify(long id, Project source){
        source.setId(id);

        source.setTags(saveTags(source.getTags()));

        return projectQueryRepository.save(source);
    }

    @DeclutterTags
    public void delete(long id){
        projectQueryRepository.deleteById(id);
    }
}
