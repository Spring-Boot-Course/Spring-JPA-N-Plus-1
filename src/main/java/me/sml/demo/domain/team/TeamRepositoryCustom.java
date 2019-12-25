package me.sml.demo.domain.team;

import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface TeamRepositoryCustom {
    List<Team> findAllJoinFetchQuerydsl();

    @EntityGraph(attributePaths = "members")
    List<Team> findAllEntityGraphQuerydsl();
}
