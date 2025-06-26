package springis.dicontainer.beandefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springis.dicontainer.AppConfig;

class BeanDefinitionTest {

  // NOTE: 애너테이션이든 XML이든 BeanDefinition 규약만 맞추면 됨
  // NOTE: CGLIB가 AppConfig를 이용해서 팩토리 빈 생성
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//  GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

  @Test
  @DisplayName("빈 설정 메타정보 확인")
  void findApplicationBean() {
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();

    for (String beanDefinitionName : beanDefinitionNames) {
      BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

      if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
        System.out.println(
            "beanDefinitionName = " + beanDefinitionName + ", beanDefinition = " + beanDefinition);
      }
    }
  }
}
