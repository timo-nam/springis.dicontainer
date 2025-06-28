package springis.dicontainer.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.Getter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

class PrototypeProviderTest {

  @Test
  void providerTest() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,
        PrototypeBean.class);

    ClientBean clientBean1 = ac.getBean(ClientBean.class);
    int count1 = clientBean1.logic();
    Assertions.assertThat(count1).isEqualTo(1);

    ClientBean clientBean2 = ac.getBean(ClientBean.class);
    int count2 = clientBean2.logic();
    Assertions.assertThat(count2).isEqualTo(1);
  }

  static class ClientBean {

    // NOTE: DI가 아니라 Dependency Lookup(의존성 탐색)
    // NOTE: ObjectProvider = ObjectFactroy + 편의 기능
    // NOTE: 딱 필요한 DL 기능만 제공
    // NOTE: 스프링에서 제공하는 라이브러리
    @Autowired
    private Provider<PrototypeBean> provider;

    public int logic() {
      PrototypeBean prototypeBean = provider.get();
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }
  }

  @Getter
  @Scope("prototype")
  static class PrototypeBean {

    private int count = 0;

    public void addCount() {
      count++;
    }

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init " + this);
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }
}
