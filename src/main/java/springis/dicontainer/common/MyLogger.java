package springis.dicontainer.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.UUID;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

// NOTE: ScopedProxyMode.TARGET_CLASS - 클래스에 적용
// NOTE: ScopedProxyMode.INTERFACES - 인터페이스에 적용
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class MyLogger {

  private String uuid;

  @Setter
  private String requestURL;

  public void log(String message) {
    System.out.println("[" + uuid + "] " + "[" + requestURL + "] " + message);
  }

  @PostConstruct
  public void init() {
    uuid = UUID.randomUUID().toString();
    System.out.println("[" + uuid + "] request scope bean init " + this);
  }

  @PreDestroy
  public void destroy() {
    System.out.println("[" + uuid + "] request scope bean destroy " + this);
  }
}
