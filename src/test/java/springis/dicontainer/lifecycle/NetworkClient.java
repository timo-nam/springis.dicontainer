package springis.dicontainer.lifecycle;

import lombok.Setter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@Setter
// NOTE: InitializingBean, DisposableBean은 스프링에서 제공하는 인터페이스 => 외부 라이브러리에 적용 불가
public class NetworkClient implements InitializingBean, DisposableBean {

  private String url;

  public NetworkClient() {
    System.out.println("생성자 호출, url = " + url);
  }

  public void connect() {
    System.out.println("connect: " + url);
  }

  public void call(String message) {
    System.out.println("call: " + url + ", message = " + message);
  }

  public void disconnect() {
    System.out.println("close: " + url);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    connect();
    call("초기화 연결 메시지");
  }

  @Override
  public void destroy() throws Exception {
    disconnect();
  }
}
