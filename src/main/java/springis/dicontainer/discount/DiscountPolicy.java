package springis.dicontainer.discount;

import springis.dicontainer.member.Member;

public interface DiscountPolicy {

  /**
   * @return 할인 금액.
   */
  int discount(Member member, int price);
}
