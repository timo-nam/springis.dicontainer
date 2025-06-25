package springis.dicontainer;

import springis.dicontainer.member.Grade;
import springis.dicontainer.member.Member;
import springis.dicontainer.member.MemberService;
import springis.dicontainer.member.MemberServiceImpl;
import springis.dicontainer.order.Order;
import springis.dicontainer.order.OrderServiceImpl;

public class OrderApp {

  public static void main(String[] args) {
    MemberService memberService = new MemberServiceImpl();
    OrderServiceImpl orderService = new OrderServiceImpl();

    long memberId = 1L;

    Member member = new Member(memberId, "memberA", Grade.VIP);
    memberService.join(member);

    Order order = orderService.createOrder(memberId, "itemA", 10_000);

    System.out.println("order = " + order);
  }
}
