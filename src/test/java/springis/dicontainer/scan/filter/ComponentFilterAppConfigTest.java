package springis.dicontainer.scan.filter;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

class ComponentFilterAppConfigTest {

  @Test
  void filterScan() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
    
    assertThrows(
        NoSuchBeanDefinitionException.class,
        () -> ac.getBean("beanA", BeanA.class)
    );

    assertThrows(
        NoSuchBeanDefinitionException.class,
        () -> ac.getBean("beanB", BeanB.class)
    );
  }

  @Configuration
  @ComponentScan(
      includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
      excludeFilters = {
          @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class),
          @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class)
      }
  )
  static class ComponentFilterAppConfig {

  }
}
