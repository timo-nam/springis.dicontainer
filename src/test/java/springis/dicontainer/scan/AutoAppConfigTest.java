package springis.dicontainer.scan;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springis.dicontainer.AutoAppConfig;
import springis.dicontainer.discount.DiscountPolicy;
import springis.dicontainer.member.MemberService;

class AutoAppConfigTest {

  @Test
  void basicScan() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    // NOTE: 기본 빈 이름은 클래스명의 맨앞 글자를 소문자로 바꾼 것
    MemberService memberService = ac.getBean("memberServiceImpl", MemberService.class);
    assertThat(memberService).isInstanceOf(MemberService.class);

    DiscountPolicy discountPolicy = ac.getBean("정률할인정책", DiscountPolicy.class);
    assertThat(discountPolicy).isInstanceOf(DiscountPolicy.class);
  }
}