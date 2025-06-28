package springis.dicontainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DicontainerApplication {

  public static void main(String[] args) {
    // NOTE: 스프링 부트는 기본적으로는 AnnotationConfigApplicationContext 사용
    // NOTE: 웹이 추가되면 AnnotationConfig`ServletWebServer`ApplicationContext 사용
    SpringApplication.run(DicontainerApplication.class, args);
  }

}
