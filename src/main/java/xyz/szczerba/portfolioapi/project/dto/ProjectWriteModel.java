package xyz.szczerba.portfolioapi.project.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public
class ProjectWriteModel {
    private String title;

    private String description;
    private String screenImgUrl;

    private String mobileImgUrl;

    private String websiteUrl;

    private String githubUrl;

    private String githubFrontEndUrl;

    private String background;
    private String style;
    private String todo;

    private List<String> tags;

    public ProjectWriteModel() {
    }

    public ProjectWriteModel(final String title, final String description, final String screenImgUrl, final String mobileImgUrl, final String websiteUrl, final String githubUrl, final String githubFrontEndUrl, final String todo, final List<String> tags) {
        this.title = title;
        this.description = description;
        this.screenImgUrl = screenImgUrl;
        this.mobileImgUrl = mobileImgUrl;
        this.websiteUrl = websiteUrl;
        this.githubUrl = githubUrl;
        this.githubFrontEndUrl = githubFrontEndUrl;
        this.todo = todo;
        this.tags = tags;
    }
}
