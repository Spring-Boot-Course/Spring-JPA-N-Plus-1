package me.sml.demo.domain.team;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {

    private final TeamRepository teamRepository;

    public List<String> findAllMemberName() {
        return teamRepository.findAll()
                .stream()
                .map(team -> team.getMembers().iterator().next().getName())
//                .map(team -> team.getMembers().get(0).getName())
                .collect(Collectors.toList());
    }

    public List<String> findAllMemberNameJoinFetch(){
        return teamRepository.findAllJoinFetch()
                .stream()
//                .map(team -> team.getMembers().get(0).getName())
                .map(team -> team.getMembers().iterator().next().getName())
                .collect(Collectors.toList());
    }

    public List<String> findAllMemberNameJoinFetchQuerydsl(){
        return teamRepository.findAllJoinFetchQuerydsl()
                .stream()
//                .map(team -> team.getMembers().get(0).getName())
                .map(team -> team.getMembers().iterator().next().getName())
                .collect(Collectors.toList());
    }

    public List<String> findAllMemberNameEntityGraph(){
        return teamRepository.findAllEntityGraph()
                .stream()
//                .map(team -> team.getMembers().get(0).getName())
                .map(team -> team.getMembers().iterator().next().getName())
                .collect(Collectors.toList());
    }

    public List<String> findAllMemberNameEntityGraphQuerydsl(){
        return teamRepository.findAllEntityGraphQuerydsl()
                .stream()
//                .map(team -> team.getMembers().get(0).getName())
                .map(team -> team.getMembers().iterator().next().getName())
                .collect(Collectors.toList());
    }



}
