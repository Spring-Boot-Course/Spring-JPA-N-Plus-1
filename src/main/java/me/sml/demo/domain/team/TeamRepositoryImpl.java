package me.sml.demo.domain.team;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static me.sml.demo.domain.team.QTeam.team;
import static me.sml.demo.domain.member.QMember.member;


@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Team> findAllJoinFetchQuerydsl() {
        return queryFactory.selectFrom(team)
                .innerJoin(team.members, member)
                .fetchJoin()
                .fetch();
    }

    @Override
    public List<Team> findAllEntityGraphQuerydsl() {
        return queryFactory.selectFrom(team)
                .fetch();
    }

}
