package springis.dicontainer.autowired;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springis.dicontainer.discount.DiscountPolicy;
import springis.dicontainer.discount.FixDiscountPolicy;
import springis.dicontainer.discount.RateDiscountPolicy;
import springis.dicontainer.member.MemberRepository;
import springis.dicontainer.member.MemoryMemberRepository;
import springis.dicontainer.order.OrderServiceImpl;

class TypeDuplicatedTest {

  // NOTE: @Qualifier로 빈 이름이 바뀌는 건 아님
  // NOTE: 1st. @Qualifier끼리 매칭 -> 2nd. 빈 이름 매칭
  // NOTE: But! @Qualifier는 명확히 짝을 지어 쓰자.

  // NOTE: @Primary는 우선순위를 지정
  // NOTE: @Primary, @Qualifier 겹치면 @Qualifier 적용
  @Test
  void resolveDuplication() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TypeDuplicatedConfig.class,
        OrderServiceImpl.class);

    OrderServiceImpl orderService = ac.getBean("orderServiceImpl", OrderServiceImpl.class);

    assertThat(orderService.getDiscountPolicy()).isInstanceOf(RateDiscountPolicy.class);
    assertThat(orderService.getDiscountPolicy()).isNotInstanceOf(FixDiscountPolicy.class);
  }

  @Configuration
  static class TypeDuplicatedConfig {

    @Bean
    public MemberRepository memberRepository() {
      return new MemoryMemberRepository();
    }

    @Bean
//    @Qualifier("fixDiscountPolicy")
    public DiscountPolicy fixDiscountPolicy() {
      return new FixDiscountPolicy();
    }

    @Bean
//    @Qualifier("mainDiscountPolicy")
    @Primary
    public DiscountPolicy rateDiscountPolicy() {
      return new RateDiscountPolicy();
    }
  }
}
