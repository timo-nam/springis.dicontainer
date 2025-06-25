package springis.dicontainer.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import springis.dicontainer.member.Grade;
import springis.dicontainer.member.Member;
import springis.dicontainer.member.MemberService;
import springis.dicontainer.member.MemberServiceImpl;

class OrderServiceTest {

  MemberService memberService = new MemberServiceImpl();
  OrderServiceImpl orderService = new OrderServiceImpl();

  @Test
  void createOrder() {
    // given
    long memberId = 1L;
    Member member = new Member(memberId, "memberA", Grade.VIP);
    memberService.join(member);

    // when
    Order order = orderService.createOrder(memberId, "itemA", 10_000);

    // then
    assertThat(order.getDiscountPrice()).isEqualTo(1_000);
  }
}