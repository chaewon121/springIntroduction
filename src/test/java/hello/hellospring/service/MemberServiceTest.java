package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void BeforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("회원가입")
    void join_test() {
        // given
        Member member = new Member();
        member.setName("채채");

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
