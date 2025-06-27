package springis.dicontainer.order;

import org.springframework.stereotype.Service;
import springis.dicontainer.discount.DiscountPolicy;
import springis.dicontainer.member.Member;
import springis.dicontainer.member.MemberRepository;

@Service
public class OrderServiceImpl implements OrderService {

  private final MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy;
//  private MemberRepository memberRepository;
//  private DiscountPolicy discountPolicy;

  // NOTE: <일반 메서드 주입> setter 대비 한 번에 여러 필드에 의존 관계 주입
//  @Autowired
//  public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//    this.memberRepository = memberRepository;
//    this.discountPolicy = discountPolicy;
//  }

  public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  // 검증용
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }
}
