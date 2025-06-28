package springis.dicontainer.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springis.dicontainer.common.MyLogger;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

  private final LogDemoService logDemoService;

  // NOTE: HTTP 요청이 들어오기 전에 logDemoController 빈을 생성할 땐 MyLogger 빈이 없음
  private final ObjectProvider<MyLogger> myLoggerProvider;

  @RequestMapping("log-demo")
  @ResponseBody
  public String logDemo(HttpServletRequest request) {
    String requestURL = request.getRequestURL().toString();

    // NOTE: HTTP 요청이 들어올 때 MyLogger 빈이 생성됐음
    MyLogger myLogger = myLoggerProvider.getObject();
    myLogger.setRequestURL(requestURL);

    myLogger.log("controller test");
    logDemoService.logic("testId");
    return "OK";
  }
}
