package springis.dicontainer.scan;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import springis.dicontainer.member.Member;

public class OptionalField {

  // NOTE: 찾는 빈이 없으면 실행 자체가 안 됨
  // NOTE: 생성자 주입에는 적용 X
  @Autowired(required = false)
  public void setNoBean1(Member member) {
    System.out.println("setNoBean1 = " + member);
  }

  // NOTE: org.springframework.lang.Nullable
  // NOTE: 생성자 주입 시 각 필드에 적용 가능
  @Autowired
  public void setNoBean2(@Nullable Member member) {
    System.out.println("setNoBean2 = " + member);
  }

  // NOTE: 옵셔널 이용
  // NOTE: 생성자 주입 시 각 필드에 적용 가능
  @Autowired
  public void setNoBean3(Optional<Member> member) {
    System.out.println("setNoBean3 = " + member);
  }
}
