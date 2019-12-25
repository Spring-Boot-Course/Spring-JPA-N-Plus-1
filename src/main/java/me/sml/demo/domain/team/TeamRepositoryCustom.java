package me.sml.demo.domain.team;

import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.Set;

public interface TeamRepositoryCustom {
    Set<Team> findAllJoinFetchQuerydsl();

    @EntityGraph(attributePaths = "members")
    Set<Team> findAllEntityGraphQuerydsl();
}
