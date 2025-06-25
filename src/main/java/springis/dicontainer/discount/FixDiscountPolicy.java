package springis.dicontainer.discount;

import springis.dicontainer.member.Grade;
import springis.dicontainer.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

  private static final int DISCOUNT_FIX_AMOUNT = 1_000;

  @Override
  public int discount(Member member, int price) {
    if (member.getGrade() == Grade.VIP) {
      return DISCOUNT_FIX_AMOUNT;
    }
    
    return 0;
  }
}
