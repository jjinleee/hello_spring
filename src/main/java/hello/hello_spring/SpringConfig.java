package hello.hello_spring;

import hello.hello_spring.repository.JdbcMemberRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//자바코드로 직접 스프링 빈 등록하는 파일
@Configuration
public class SpringConfig {

    private final  DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource=dataSource;
    }

    @Bean   //스프링 빈을 내가 등록하겠다
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
