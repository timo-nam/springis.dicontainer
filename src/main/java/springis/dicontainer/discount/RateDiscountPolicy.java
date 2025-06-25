package springis.dicontainer.discount;

import springis.dicontainer.member.Grade;
import springis.dicontainer.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {

  // 10% 할인
  private static final int DISCOUNT_PERCENT = 10;

  @Override
  public int discount(Member member, int price) {
    if (member.getGrade() == Grade.VIP) {
      return price * DISCOUNT_PERCENT / 100;
    }

    return 0;
  }
}
