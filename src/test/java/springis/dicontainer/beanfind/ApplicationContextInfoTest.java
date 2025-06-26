package springis.dicontainer.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springis.dicontainer.AppConfig;

class ApplicationContextInfoTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  @DisplayName("모든 빈 출력하기")
  void findAllBeans() {
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();

    for (String beanDefinitionName : beanDefinitionNames) {
      Object bean = ac.getBean(beanDefinitionName);
      System.out.println("name = " + beanDefinitionName + ", object = " + bean);
    }
  }

  @Test
  @DisplayName("애플리케이션 빈 출력하기")
  void findApplicationBeans() {
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();

    for (String beanDefinitionName : beanDefinitionNames) {
      // NOTE: ApplicationContext는 getBeanDefinition() 없음
      BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

      // NOTE: ROLE_APPLICATION - 사용자 정의 빈
      // NOTE: ROLE_INFRASTRUCTURE - 스프링 내부 빈
      if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
        Object bean = ac.getBean(beanDefinitionName);
        System.out.println("name = " + beanDefinitionName + ", object = " + bean);
      }
    }
  }
}
