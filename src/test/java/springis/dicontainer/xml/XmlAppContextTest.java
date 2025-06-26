package springis.dicontainer.xml;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import springis.dicontainer.member.MemberService;

class XmlAppContextTest {

  @Test
  void xmlAppContext() {
    ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    MemberService memberService = ac.getBean("memServ", MemberService.class);
    assertThat(memberService).isInstanceOf(MemberService.class);
  }
}
