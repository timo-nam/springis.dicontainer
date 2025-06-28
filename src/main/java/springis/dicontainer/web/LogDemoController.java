package springis.dicontainer.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springis.dicontainer.common.MyLogger;

@Controller
//@RequiredArgsConstructor
public class LogDemoController {

  private final LogDemoService logDemoService;

  // NOTE: HTTP 요청이 들어오기 전에 logDemoController 빈을 생성할 땐 MyLogger 빈이 없음
//  private final ObjectProvider<MyLogger> myLoggerProvider;
  private final MyLogger myLogger;

  public LogDemoController(LogDemoService logDemoService, MyLogger myLogger) {
    this.logDemoService = logDemoService;
    this.myLogger = myLogger;

    // NOTE: MyLogger를 확장한 MyLogger$$SpringCGLIB$$0 프록시
    // NOTE: 1개의 MyLogger$$SpringCGLIB$$0 프록시가 여러개의 request 스코프 빈을 대신 호출
    System.out.println("myLogger = " + myLogger.getClass());
  }

  @RequestMapping("log-demo")
  @ResponseBody
  public String logDemo(HttpServletRequest request) {
    String requestURL = request.getRequestURL().toString();

    // NOTE: HTTP 요청이 들어올 때 MyLogger 빈이 생성됐음
//    MyLogger myLogger = myLoggerProvider.getObject();
    myLogger.setRequestURL(requestURL);

    myLogger.log("controller test");
    logDemoService.logic("testId");
    return "OK";
  }
}
