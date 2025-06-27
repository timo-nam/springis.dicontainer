package springis.dicontainer.order;

import lombok.Getter;
import org.springframework.stereotype.Service;
import springis.dicontainer.discount.DiscountPolicy;
import springis.dicontainer.member.Member;
import springis.dicontainer.member.MemberRepository;

// NOTE: Lombok + 생성자 주입
//@RequiredArgsConstructor
@Getter
@Service
public class OrderServiceImpl implements OrderService {

  private final MemberRepository memberRepository;

  // NOTE: DiscountPolicy 타입으로 조회하면 빈이 2개 - discountPolicy(수동), 정률할인정책(자동)
  // NOTE: 필드명 2개 빈 중에 선택
  // NOTE: 1st. 타입 매칭 -> 2nd. 필드명/매개변수명 매칭
  private final DiscountPolicy discountPolicy;
//  private MemberRepository memberRepository;
//  private DiscountPolicy discountPolicy;

  // NOTE: <일반 메서드 주입> setter 대비 한 번에 여러 필드에 의존 관계 주입
//  @Autowired
//  public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//    this.memberRepository = memberRepository;
//    this.discountPolicy = discountPolicy;
//  }

  public OrderServiceImpl(
      MemberRepository memberRepository,
      /*@Qualifier("mainDiscountPolicy")*/ DiscountPolicy discountPolicy
  ) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }
}
