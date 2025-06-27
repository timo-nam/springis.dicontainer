package springis.dicontainer.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// NOTE: <스프링 빈의 이벤트 생명주기> 컨테이너 생성 -> 빈 생성 -> 의존 관계 주입 -> 초기화 콜백 -> 사용 -> 소멸 전 콜백 -> 컨테이너 종료
class BeanLifeCycleTest {

  @Test
  public void lifeCycleTest() {
    // NOTE: ApplicationContext는 직접 close() 불가
    ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(
        LifeCycleConfig.class);

    NetworkClient client = ac.getBean(NetworkClient.class);

    // NOTE: close() - 스프링 컨테이너를 직접 내림
    ac.close();
  }

  @Configuration
  static class LifeCycleConfig {

    // NOTE: 빈 생성과 초기화(무거운 동작)를 분리하는 것이 좋음
    // NOTE: destroyMethod는 기본값으로 "close" / "shutdown"을 추론
    // NOTE: 추론 기능을 안 쓰고 싶으면 destroyMethod = ""
//    @Bean(initMethod = "init"/* , destroyMethod = "close" */)
    @Bean
    public NetworkClient networkClient() {
      NetworkClient networkClient = new NetworkClient();
      networkClient.setUrl("http://watalk.sbs.co.kr");
      return networkClient;
    }
  }
}
