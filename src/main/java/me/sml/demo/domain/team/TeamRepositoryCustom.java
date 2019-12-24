package me.sml.demo.domain.team;

import java.util.List;

public interface TeamRepositoryCustom {
    List<Team> findAllJoinFetchQuerydsl();
}
