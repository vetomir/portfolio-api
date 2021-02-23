package xyz.szczerba.portfolioapi.project;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
interface SqlProjectRepository extends ProjectQueryRepository, JpaRepository<Project, Long> {
    long count();
}
