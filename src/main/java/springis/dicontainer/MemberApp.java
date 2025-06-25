package springis.dicontainer;

import springis.dicontainer.member.Grade;
import springis.dicontainer.member.Member;
import springis.dicontainer.member.MemberService;

public class MemberApp {

  public static void main(String[] args) {
    AppConfig appConfig = new AppConfig();

    // NOTE: memberService()가 구현체를 넣어줌 => 의존성 주입(DI;Dependency Injection)
    MemberService memberService = appConfig.memberService();

    Member member = new Member(1L, "memberA", Grade.VIP);
    memberService.join(member);

    Member findMember = memberService.findMember(1L);

    System.out.println("new member = " + member.getName());
    System.out.println("find member = " + findMember.getName());
  }
}
