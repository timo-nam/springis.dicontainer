package springis.dicontainer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springis.dicontainer.member.Grade;
import springis.dicontainer.member.Member;
import springis.dicontainer.member.MemberService;
import springis.dicontainer.order.Order;
import springis.dicontainer.order.OrderService;

public class OrderApp {

  public static void main(String[] args) {
    // NOTE: ApplicationContext extends BeanFactory
    // NOTE: AnnotationConfigApplicationContext implements ApplicationContext
    // NOTE: 진짜 순수하게 빈을 관리하는 역할은 BeanFactory
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    OrderService orderService = ac.getBean("orderService", OrderService.class);

    long memberId = 1L;

    Member member = new Member(memberId, "memberA", Grade.VIP);
    memberService.join(member);

    Order order = orderService.createOrder(memberId, "itemA", 10_000);

    System.out.println("order = " + order);
  }
}
