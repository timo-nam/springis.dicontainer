package springis.dicontainer.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import springis.dicontainer.common.MyLogger;

@RequiredArgsConstructor
@Service
public class LogDemoService {

  // NOTE: MyLogger 덕분에 웹 관련 정보를 서비스 계층까지 안 넘기고 처리
  private final ObjectProvider<MyLogger> myLoggerProvider;

  public void logic(String id) {
    MyLogger myLogger = myLoggerProvider.getObject();
    myLogger.log("service id: " + id);
  }
}
