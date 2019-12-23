package me.sml.demo.domain.team;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sml.demo.domain.member.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

    private String name;

    @Builder
    public Team(List<Member> members, String name) {
        this.name = name;
        if(members != null){
            this.members = members;
        }
    }

    public void addMember(Member member){
        this.members.add(member);
        member.addTeam(this);
    }
}
