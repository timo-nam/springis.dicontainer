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
import springis.dicontainer.member.MemberRepository;
import springis.dicontainer.member.MemoryMemberRepository;

class ApplicationContextSameBeanFindTest {

  ApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

  @Test
  @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면, 중복 오류가 발생")
  void findBeanByTypeDuplicate() {
    assertThrows(
        // NOTE: 빈이 중복될 때 => NoUniqueBeanDefinitionException
        NoUniqueBeanDefinitionException.class,
        () -> ac.getBean(MemberRepository.class)
    );
  }

  @Test
  @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면, 빈 이름을 지정")
  void findBeanByName() {
    MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
    MemberRepository memberRepository2 = ac.getBean("memberRepository2", MemberRepository.class);

    assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
    assertThat(memberRepository2).isInstanceOf(MemberRepository.class);
    assertThat(memberRepository1).isNotSameAs(memberRepository2);
  }

  @Test
  @DisplayName("특정 타입 모두 조회")
  void findAllBeansByType() {
    // NOTE: getBeansOfType() - 특정 타입의 빈 모두 조회
    Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);

    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + ", vale = " + beansOfType.get(key));
    }

    System.out.println("beansOfType = " + beansOfType);

    assertThat(beansOfType.size()).isEqualTo(2);
  }

  @Configuration
  static class SameBeanConfig {

    @Bean
    public MemberRepository memberRepository1() {
      return new MemoryMemberRepository();
    }

    @Bean
    public MemberRepository memberRepository2() {
      return new MemoryMemberRepository();
    }
  }
}
