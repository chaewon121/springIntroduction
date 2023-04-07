package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //-> 스프링 컨테이너에 '빈'으로 등록해주기 위해
/**
 * @Controller,@Service,@Repository -> 스프링 컨테이너에 '빈'으로 등록해주기 위해
 * 빈 등록 할때, 기본적으로 싱글톤으로 등록한다.
 */
public class MemberController {

    private final MemberService memberService;

    @Autowired // -> 스프링이 연관된 객체를 찾아서 넣어준다. -> DI(객체의 읜존관계를 외부에서 넣어주는 것)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * Autowired -> 객체 생성 시점에 스프링 컨테이너에서 해당 빈을 찾아 주입한다.
     * 스프링 빈으로 등록된 것만 동작한다.
     */

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
