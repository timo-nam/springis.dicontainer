package springis.dicontainer.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springis.dicontainer.AppConfig;
import springis.dicontainer.member.MemberRepository;
import springis.dicontainer.member.MemberServiceImpl;
import springis.dicontainer.order.OrderServiceImpl;

class ConfigurationSingletonTest {

  @Test
  void configurationTest() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
    OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
    MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

    System.out.println(
        "memberService -> memberRepository = " + memberService.getMemberRepository());
    System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
    System.out.println("memberRepository = " + memberRepository);

    assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
    assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
  }

  @Test
  void configurationDeep() {
    // NOTE: @Configuration을 보고 AppConfig$$SpringCGLIB$$0 클래스를 생성
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // NOTE: AppConfig$$SpringCGLIB$$0는 AppConfig의 자식 타입
    AppConfig bean = ac.getBean(AppConfig.class);

    System.out.println("bean = " + bean.getClass());
  }
}
