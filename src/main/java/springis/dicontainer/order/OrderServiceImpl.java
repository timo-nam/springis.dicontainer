package springis.dicontainer.order;

import springis.dicontainer.discount.DiscountPolicy;
import springis.dicontainer.discount.FixDiscountPolicy;
import springis.dicontainer.member.Member;
import springis.dicontainer.member.MemberRepository;
import springis.dicontainer.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

  private final MemberRepository memberRepository = new MemoryMemberRepository();
  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }
}
