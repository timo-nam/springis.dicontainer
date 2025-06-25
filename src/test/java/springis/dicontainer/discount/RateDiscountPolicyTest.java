package springis.dicontainer.discount;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import springis.dicontainer.member.Grade;
import springis.dicontainer.member.Member;

class RateDiscountPolicyTest {

  DiscountPolicy discountPolicy = new RateDiscountPolicy();

  @Test
  // NOTE: @DisplayName 활용, `Run tests using: IntelliJ IDEA`로 적용
  @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
  void vip_o() {
    // given
    Member member = new Member(1L, "memberVIP", Grade.VIP);

    // when
    int discount = discountPolicy.discount(member, 10_000);

    // then
    assertThat(discount).isEqualTo(1_000);
  }

  @Test
  @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
  void vip_x() {
    // given
    Member member = new Member(1L, "memberBASIC", Grade.BASIC);

    // when
    int discount = discountPolicy.discount(member, 10_000);

    // then
    assertThat(discount).isEqualTo(0);
  }
}