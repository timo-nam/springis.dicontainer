package springis.dicontainer.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springis.dicontainer.discount.DiscountPolicy;
import springis.dicontainer.discount.FixDiscountPolicy;
import springis.dicontainer.discount.RateDiscountPolicy;

class ApplicationContextExtendsFindTest {

  ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

  @Test
  @DisplayName("부모 타입으로 조회 시 자식 타입이 둘 이상 있으면, 중복 오류가 발생")
  void findBeanByParentTypeDuplicate() {
    assertThrows(
        NoUniqueBeanDefinitionException.class,
        () -> ac.getBean(DiscountPolicy.class)
    );
  }

  @Test
  @DisplayName("부모 타입으로 조회 시 자식 타입이 둘 이상 있으면, 빈 이름을 지정")
  void findBeanByParentTypeBeanName() {
    DiscountPolicy fixDiscountPolicy = ac.getBean("fixDiscountPolicy", DiscountPolicy.class);
    DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);

    assertThat(fixDiscountPolicy).isInstanceOf(FixDiscountPolicy.class);
    assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  @DisplayName("특정 하위 타입으로 조회")
  void findBeanBySubType() {
    RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
    assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  @DisplayName("부모 타입으로 모두 조회")
  void findAllBeansByParentType() {
    Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);

    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + ", value = " + beansOfType.get(key));
    }

    assertThat(beansOfType.size()).isEqualTo(2);
  }

  @Test
  @DisplayName("최상위 타입으로 모두 조회")
  void findAllBeansByObjectType() {
    Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);

    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + ", value = " + beansOfType.get(key));
    }
  }

  @Configuration
  static class TestConfig {

    @Bean
    public DiscountPolicy fixDiscountPolicy() {
      return new FixDiscountPolicy();
    }

    @Bean
    public DiscountPolicy rateDiscountPolicy() {
      return new RateDiscountPolicy();
    }
  }
}
