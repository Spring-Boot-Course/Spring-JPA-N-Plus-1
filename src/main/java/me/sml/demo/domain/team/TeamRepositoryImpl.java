package me.sml.demo.domain.team;

import com.google.common.collect.Sets;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

import static me.sml.demo.domain.team.QTeam.team;
import static me.sml.demo.domain.member.QMember.member;


@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Set<Team> findAllJoinFetchQuerydsl() {
        return
                Sets.newLinkedHashSet(
                        queryFactory.selectFrom(team)
                                .innerJoin(team.members, member)
                                .fetchJoin()
                                .fetch()
                );
    }

    @Override
    public Set<Team> findAllEntityGraphQuerydsl() {
        return Sets.newLinkedHashSet(
                queryFactory.selectFrom(team)
                .fetch()
        );
    }

}
