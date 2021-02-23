package xyz.szczerba.portfolioapi.project;

import java.util.List;
import java.util.Optional;

public interface TagQueryRepository {
    boolean existsByName(final String name);

    Tag save(Tag tag);

    Optional<Tag> getByName(String name);

    List<Tag> findAll();

    void delete(Tag tag);

    List<String> findUnusedTags();

    void clear();
}
