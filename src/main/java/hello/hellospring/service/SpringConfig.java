package hello.hellospring.service;

import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 자바 코드로 직접 스프링빈 등록하기
 */
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    //@Autowired // 생성자가 하나이므로 제거 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /**
     * 스프링 데이터 JPA가 SpringDataJpaMemberRepository 를
     * 스프링 빈으로 자동 등록해준다.
     */
//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
////        return new JpaMemberRepository(em);
//        return new JpaMemberRepository(em);
//    }
}
