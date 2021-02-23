package xyz.szczerba.portfolioapi.project;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Getter
@Setter
@Table(name = "projects")
class Project {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String title;
    private String description;
    private String screenImgUrl;
    private String mobileImgUrl;
    private String websiteUrl;
    private String githubUrl;
    private String githubFrontUrl;
    private String todo;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "projects_tags",
            joinColumns = @JoinColumn(
                    name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> tags = new HashSet<>();
}
