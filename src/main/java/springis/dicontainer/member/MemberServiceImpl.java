package springis.dicontainer.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// NOTE: 인터페이스의 구현체가 하나라면 클래스 이름을 `*Impl`로 짓는 게 관례
// NOTE: (추상화에만 의존 => DIP 준수) -> OCP 준수
// NOTE: 사용 영역은 로직에만 집중 => 아키텍처 수준에서는 SoC, 클래스 레벨에서는 SRP 준수

// NOTE: @Service - 아무 부가 기능 없음
@Service
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  // NOTE: 생성자를 호출하는 외부에서 구현체를 선택
  @Autowired
  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  // 검증용
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }

  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }

  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }
}
