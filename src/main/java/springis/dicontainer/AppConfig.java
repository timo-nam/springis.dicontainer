package springis.dicontainer;

import springis.dicontainer.discount.DiscountPolicy;
import springis.dicontainer.discount.RateDiscountPolicy;
import springis.dicontainer.member.MemberRepository;
import springis.dicontainer.member.MemberService;
import springis.dicontainer.member.MemberServiceImpl;
import springis.dicontainer.member.MemoryMemberRepository;
import springis.dicontainer.order.OrderService;
import springis.dicontainer.order.OrderServiceImpl;

// NOTE: 애플리케이션이 구성 영역과 사용 영역으로 구분 => 관심사의 분리(SoC;Separation of Concerns)
// NOTE: 구성 영역은 조립 방식이 명확
// NOTE: 구성 영역에서 실행 흐름에 대한 제어 => 제어의 역전(IoC;Inversion of Control) => 프레임워크
// NOTE: 구성을 담당하는 프레임워크 기반을 IoC 컨테이너/DI 컨테이너/어셈블러/오브젝트 팩토리라고 함
public class AppConfig {

  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

  public DiscountPolicy discountPolicy() {
//    return new FixDiscountPolicy();
    return new RateDiscountPolicy();
  }

  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }
}
