package xyz.szczerba.portfolioapi.project;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tags")
class Tag {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name;
    //private String githubUrl;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private Set<Project> projects = new HashSet<>();

    @PersistenceConstructor
    public Tag() {
    }

    public Tag(final String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(final Set<Project> projects) {
        this.projects = projects;
    }
}

