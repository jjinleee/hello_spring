package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store=new HashMap<>();
    private static long sequence=0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);   //sequence값+1
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));      //널이반환될 가능성 있으면 optional로 감싼다
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        //같은 이름이 있을 경우 반환-자바 람다식 이용, 이 반환이 optional로 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //values는 member, member들이 반환
    }

    //Test 랜덤순서로 인한 오류가 나지 않도록 지워주는 메소드
    public void clearStore(){
        store.clear();
    }
}
