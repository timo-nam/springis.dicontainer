package springis.dicontainer.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

  // NOTE: 개발에서 무상태(stateless)는 정말 중요, 싱글톤 빈에서는 특히 더 중요
  @Test
  void statefulServiceSingleton() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    StatefulService statefulService = ac.getBean("statefulService", StatefulService.class);

    // 사용자A 주문
    statefulService.order("userA", 10_000);

    // 사용자B 주문
    statefulService.order("userB", 20_000);

    // 사용자A 주문 금액 조회
    int price = statefulService.getPrice();
    System.out.println("price = " + price);
    assertThat(price).isEqualTo(20_000);
  }

  static class TestConfig {

    @Bean
    public StatefulService statefulService() {
      return new StatefulService();
    }
  }
}