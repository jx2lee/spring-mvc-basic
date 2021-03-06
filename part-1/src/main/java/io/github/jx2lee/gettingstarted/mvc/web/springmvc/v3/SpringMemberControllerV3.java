package io.github.jx2lee.gettingstarted.mvc.web.springmvc.v3;

import io.github.jx2lee.gettingstarted.mvc.domain.member.Member;
import io.github.jx2lee.gettingstarted.mvc.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping(value = "/new-form")
    // GetMapping 대신 @RequestMapping(value = "/new-form", method = RequestMethod.GET) 써도 되지만
    // 귀찮으니 어노테이션 하나로 끝내자
    public String newForm() {
        return "new-form";
    }

    @PostMapping(value = "/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    @GetMapping()
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
     }
}
