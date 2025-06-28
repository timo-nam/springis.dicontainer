package springis.dicontainer.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.UUID;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("request")
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
