package springis.dicontainer.discount;

import org.springframework.stereotype.Component;
import springis.dicontainer.member.Grade;
import springis.dicontainer.member.Member;

// NOTE: 빈 자동 등록 시 이름 지정 가능
@Component("정률할인정책")
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
