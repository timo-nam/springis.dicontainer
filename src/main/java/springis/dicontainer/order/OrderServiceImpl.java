package springis.dicontainer.order;

import springis.dicontainer.discount.DiscountPolicy;
import springis.dicontainer.discount.RateDiscountPolicy;
import springis.dicontainer.member.Member;
import springis.dicontainer.member.MemberRepository;
import springis.dicontainer.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

  private final MemberRepository memberRepository = new MemoryMemberRepository();
  // NOTE: DIP 위반(구체 클래스에 의존) -> OCP 위반(구체 클래스 변경 시 코드 변경 필요)
  //  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
  private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }
}
