package xyz.szczerba.portfolioapi.project;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
interface SqlTagRepository extends TagQueryRepository, JpaRepository<Tag, Long> {
    boolean existsByName(final String name);

    @Override
    void delete(Tag tag);

    @Query("select t.name from Tag t where t.projects is empty")
    List<String> findUnusedTags();

    @Transactional
    @Modifying
    @Query("delete from Tag t where t.projects is empty")
    void clear();
}
