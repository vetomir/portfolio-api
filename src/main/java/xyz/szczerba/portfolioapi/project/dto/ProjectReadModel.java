package xyz.szczerba.portfolioapi.project.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public
class ProjectReadModel {
    private long id;
    private String title;
    private String description;
    private String screenImgUrl;
    private String mobileImgUrl;
    private String websiteUrl;
    private String githubUrl;
    private String todo;
    private String background;
    private String style;
    private List<String> tags;

}
