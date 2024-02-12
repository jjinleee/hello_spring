package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    //테스트와 같은 인스턴스를 쓰기 위해 수정
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }  //constructor-직접넣는것이 아니라 외부에서 넣어주도록 바꿈


    //회원가입
    public Long join(Member member){
        //같은이름이 있는 중복 회원안됨
        //Member member1 = result.get(); 이렇게해도되는데 Member로 직접꺼내는걸 권장x

        validateDuplication(member);  //중복회원검증 메소드 사용
        memberRepository.save(member);
        return member.getId();

    }


    //메소드로 추출 , 단축키: command+option+m
    private void validateDuplication(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                        throw new IllegalStateException(("이미 존재하는 회원입니다."));
                    });  //값이 있으면 이게 작동, null일 가능성때문에 Optional로 감쌈
    }


    //전체회원조회
    public List<Member>  findMembers(){
       return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById((memberId));
    }




}
