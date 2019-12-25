package me.sml.demo.domain.team;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamRepositoryCustom {

    @Query("SELECT t FROM Team t JOIN FETCH t.members")
    List<Team> findAllJoinFetch();

    @EntityGraph(attributePaths = "members")
    @Query("select t from Team t")
    List<Team> findAllEntityGraph();
}
