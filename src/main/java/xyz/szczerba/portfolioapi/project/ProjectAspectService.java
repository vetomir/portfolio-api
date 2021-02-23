package xyz.szczerba.portfolioapi.project;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Aspect
@Service
class ProjectAspectService {
    private final static Logger logger = LoggerFactory.getLogger(ProjectAspectService.class);
    private final TagQueryRepository tagQueryRepository;

    ProjectAspectService(final TagQueryRepository tagQueryRepository) {
        this.tagQueryRepository = tagQueryRepository;
    }

    @Pointcut("@annotation(xyz.szczerba.portfolioapi.project.DeclutterTags)")
    void InspectTags(){}

    @After("InspectTags()")
    void clearTagRepo(){
        List<String> unusedTags = tagQueryRepository.findUnusedTags();
        if(unusedTags.size() > 0){
            logger.info("Deleted unused tags: {}", unusedTags);
            tagQueryRepository.clear();
        }
        else
            logger.info("All tags are in use");
    }
}
