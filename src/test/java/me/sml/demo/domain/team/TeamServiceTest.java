package me.sml.demo.domain.team;

import me.sml.demo.domain.member.Member;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamRepository teamRepository;


    @Before
    public void setup() {

        List<Team> teams = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Team team = Team.builder()
                    .name("상민팀" + i)
                    .build();

            team.addMember(
                    Member.builder()
                            .name("이상민" + i)
                            .build()
            );

            teams.add(team);
        }
        teamRepository.saveAll(teams);
    }

    @Test
    public void N1_문제발생() throws Exception {
        //given

        //when
        List<String> allMemberName = teamService.findAllMemberName();

        //then
        assertThat(allMemberName.size()).isEqualTo(10);
    }

    @Test
    public void JOIN_FETCH로_인해_N1문제_발생하지않음(){

        //when
        List<String> allMemberName = teamService.findAllMemberNameJoinFetch();

        //then
        assertThat(allMemberName.size()).isEqualTo(10);
    }

}