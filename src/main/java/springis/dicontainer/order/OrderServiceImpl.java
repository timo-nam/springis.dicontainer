package springis.dicontainer.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springis.dicontainer.discount.DiscountPolicy;
import springis.dicontainer.member.Member;
import springis.dicontainer.member.MemberRepository;

// NOTE: Lombok + 생성자 주입
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

  // 검증용
  @Getter
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

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }
}
