package springis.dicontainer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springis.dicontainer.member.Grade;
import springis.dicontainer.member.Member;
import springis.dicontainer.member.MemberService;

public class MemberApp {

  public static void main(String[] args) {
    // NOTE: 런타임에 memberService()가 구현체를 넣어줌 => 의존성 주입(DI;Dependency Injection)
//    AppConfig appConfig = new AppConfig();
//    MemberService memberService = appConfig.memberService();

    // NOTE: 사실 사용 영역에서 빈을 '조회'하는 방식은 DI가 아니라 Service Locator 패턴
    // NOTE: ApplicationContext == 스프링 컨테이너
    // NOTE: AppConfig 클래스를 스펙으로 해서 애플리케이션을 구성
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService = ac.getBean("memberService", MemberService.class);

    Member member = new Member(1L, "memberA", Grade.VIP);
    memberService.join(member);

    Member findMember = memberService.findMember(1L);

    System.out.println("new member = " + member.getName());
    System.out.println("find member = " + findMember.getName());
  }
}
