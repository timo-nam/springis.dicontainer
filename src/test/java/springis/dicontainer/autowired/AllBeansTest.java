package springis.dicontainer.autowired;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springis.dicontainer.discount.DiscountPolicy;
import springis.dicontainer.member.Grade;
import springis.dicontainer.member.Member;

class AllBeansTest {

  @Test
  void findAllBeans() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(
        TypeDuplicatedTest.TypeDuplicatedConfig.class,
        DiscountService.class
    );
    DiscountService discountService = ac.getBean(DiscountService.class);

    Member member = new Member(1L, "userA", Grade.VIP);
    int discountPrice = discountService.discount(member, 10_000, "fixDiscountPolicy");

    Assertions.assertThat(discountPrice).isEqualTo(1_000);
  }

  static class DiscountService {

    // NOTE: 컬렉션 타입의 필드가 있으면 특정 타입의 빈을 모두 주입
    // NOTE: 전략 패턴을 구현하기 용이
    private final List<DiscountPolicy> policyList;
    private final Map<String, DiscountPolicy> policyMap;

    public DiscountService(List<DiscountPolicy> policyList, Map<String, DiscountPolicy> policyMap) {
      this.policyList = policyList;
      this.policyMap = policyMap;
      System.out.println("policyList = " + policyList);
      System.out.println("policyMap = " + policyMap);
    }

    public int discount(Member member, int price, String discountCode) {
      DiscountPolicy discountPolicy = policyMap.get(discountCode);

      System.out.println("discountCode = " + discountCode);
      System.out.println("discountPolicy = " + discountPolicy);

      return discountPolicy.discount(member, price);
    }
  }
}
