package springis.dicontainer.member;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springis.dicontainer.AppConfig;

class MemberServiceTest {

  MemberService memberService;
  
  // NOTE: @BeforeEach
  @BeforeEach
  void beforeEach() {
    AppConfig appConfig = new AppConfig();
    memberService = appConfig.memberService();
  }

  @Test
  void join() {
    // given
    Member member = new Member(1L, "memberA", Grade.VIP);

    // when
    memberService.join(member);
    Member findMember = memberService.findMember(1L);

    // then
    // NOTE: junit이 아니라 assertj
    assertThat(findMember).isSameAs(member);
  }
}