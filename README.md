# Spring JPA N+1

## N+1 문제 발생
FetchType.LAZY로 설정이 되어있어도, N+1이 발생할 수 있다.

```java
teamRepository.findAll()
                .stream()
                .map(team -> team.getMembers().get(0).getName())
                .collect(Collectors.toList());
```
> 문제의 상황: findAll()까지는 잘 가져오고, 찾아온 엔티티들에 대해 순차적으로 연관관계에 있는 엔티티를 조회할 때 하나씩 조회

## 해결방법
1. Fetch Join 사용

```java
public interface TeamRepository implements JPARepository<Team, Long> {
    
    @Query("SELECT t FROM TEAM t JOIN FETCH t.members")
    List<Team> findAllJoinFetch();
}
```

```java
/**
* querydsl 사용 시
* */ 
queryFactory.selectFrom(team)
                .innerJoin(team.members, member)
                .fetchJoin()
                .fetch()
```

2. Entity Graph 사용

```java
public interface TeamRepository implements JPARepository<Team, Long> {
    
    @EntityGrapth(attributePaths = "members")
    @Query("SELECT t FROM Team t")
    List<Team> findAllEntityGraph();
}
```
## 또다른 문제상황
팀(엔티티) 내의 멤버(엔티티)의 이름을 모두 출력하는 메서드를 작성 시 <strong>카테이션 곱</strong>에 의해 중복되서 나오는 문제 발생

해결방법 1. Set을 사용. Set은 중복된 값을 허용하지 않기 때문에 중복을 제거
```java
Sets.newLinkedHashSet(
                    queryFactory.selectFrom(team)
                            .innerJoin(team.members, member)
                            .fetchJoin()
                            .fetch()
                );
```

해결방법 2. DISTINCT 키워드 사용