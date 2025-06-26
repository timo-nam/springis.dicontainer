package springis.dicontainer.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springis.dicontainer.AppConfig;
import springis.dicontainer.member.MemberService;
import springis.dicontainer.member.MemberServiceImpl;

class ApplicationContextBasicFindTest {

  ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  @DisplayName("빈 이름으로 조회")
  void findBeanByName() {
    MemberService memberService = ac.getBean("memberService", MemberService.class);

    // 구체 타입으로 확인
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    // 추상 타입으로 확인
    assertThat(memberService).isInstanceOf(MemberService.class);
  }

  @Test
  @DisplayName("이름 없이 타입만으로 조회")
  void findBeanByType() {
    MemberService memberService = ac.getBean(MemberService.class);

    // 구체 타입으로 확인
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    // 추상 타입으로 확인
    assertThat(memberService).isInstanceOf(MemberService.class);
  }

  @Test
  @DisplayName("구체 타입으로 조회")
  void findBeanByConcreteType() {
    MemberService memberService = ac.getBean(MemberServiceImpl.class);

    // 구체 타입으로 확인
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    // 추상 타입으로 확인
    assertThat(memberService).isInstanceOf(MemberService.class);
  }

  @Test
  @DisplayName("빈 이름으로 조회 X")
  void findBeanByNameX() {
    // NOTE: 예외 발생 확인은 assertj가 아니라 junit
    assertThrows(
        // NOTE: 찾는 빈이 없을 때 => NoSuchBeanDefinitionException
        NoSuchBeanDefinitionException.class,
        () -> ac.getBean("Timo Nam", MemberService.class)
    );
  }
}
