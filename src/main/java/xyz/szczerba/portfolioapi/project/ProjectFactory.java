package xyz.szczerba.portfolioapi.project;


import xyz.szczerba.portfolioapi.project.dto.ProjectReadModel;
import xyz.szczerba.portfolioapi.project.dto.ProjectWriteModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ProjectFactory {
    public static List<Project> toEntity(List<ProjectWriteModel> projectWriteModels) {
        return projectWriteModels.stream()
                .map(ProjectFactory::toEntity)
                .collect(Collectors.toList());
    }

    public static Project toEntity(ProjectWriteModel projectWriteModel) {
        Project project = new Project();
        project.setTitle(projectWriteModel.getTitle());
        project.setDescription(projectWriteModel.getDescription());
        project.setScreenImgUrl(projectWriteModel.getScreenImgUrl());
        project.setMobileImgUrl(projectWriteModel.getMobileImgUrl());
        project.setGithubUrl(projectWriteModel.getGithubUrl());
        project.setGithubFrontUrl(projectWriteModel.getGithubFrontEndUrl());
        project.setTodo(projectWriteModel.getTodo());
        if(projectWriteModel.getTags() != null){
            project.setTags(projectWriteModel.getTags().stream()
                    .filter(x -> !x.isBlank())
                    .distinct()
                    .map(Tag::new)
                    .collect(Collectors.toSet()));
        }
        return project;
    }

    private static List<String> extractTags(String content) {
        String text = content;
        String[] words = text.split(" ");
        List<String> tags = new ArrayList<String>();

        for ( String word : words) {
            if (word.substring(0, 1).equals("#")) {
                tags.add(word);
            }
        }
        return tags;
    }

    public static List<ProjectReadModel> toDto(List<Project> projects) {
        if(projects != null){
            return projects.stream()
                    .map(ProjectFactory::toDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static ProjectReadModel toDto(Project project) {
        return ProjectReadModel.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .websiteUrl(project.getWebsiteUrl())
                .githubUrl(project.getGithubUrl())
                .mobileImgUrl(project.getMobileImgUrl())
                .screenImgUrl(project.getScreenImgUrl())
                .tags(project.getTags().stream()
                .map(Tag::getName)
                .collect(Collectors.toList()))
                .todo(project.getTodo())
                .build();
    }
}
