package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // ->스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional
/**
 * 테스트 케이스에 이 애노테이션이 있으면,
 * 테스트 시작 전에 트랜잭션을 시작하고,
 * 테스트 완료 후에 항상 롤백한다.
 * 이렇게 하면 DB에 데이터가 남지 않으므로
 * 다음 테스트에 영향을 주지 않는다.
 */
public class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입")
    void join_test() {
        // given
        Member member = new Member();
        member.setName("채채2");

        // when
        Long expected = memberService.join(member);

        memberService.findOne(expected);
        // then
        assertThat(member.getName()).isEqualTo(memberService.findOne(expected).get().getName());
    }

    @Test
    @DisplayName("중복 회원 예외")
    void join_test_예외() {
        // given
        Member member1 = new Member();
        member1.setName("채채1");

        Member member2 = new Member();
        member2.setName("채채1");

        // when
        Long expected = memberService.join(member1);
        assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
    }
}
