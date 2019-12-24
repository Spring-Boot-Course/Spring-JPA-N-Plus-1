package me.sml.demo.domain.team;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sml.demo.domain.member.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {

    private final TeamRepository teamRepository;

    public List<String> findAllMemberName() {
        return teamRepository.findAll()
                .stream()
                .map(team -> team.getMembers().get(0).getName())
                .collect(Collectors.toList());
    }

    public List<String> findAllMemberNameJoinFetch(){
        return teamRepository.findAllJoinFetch()
                .stream()
                .map(team -> team.getMembers().get(0).getName())
                .collect(Collectors.toList());
    }

}
