package springis.dicontainer.member;

// NOTE: 인터페이스의 구현체가 하나라면 클래스 이름을 `*Impl`로 짓는 게 관례
// NOTE: (추상화에만 의존 => DIP 준수) -> OCP 준수
// NOTE: 사용 영역은 로직에만 집중 => 아키텍처 수준에서는 SoC, 클래스 레벨에서는 SRP 준수
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  // NOTE: 생성자를 호출하는 외부에서 구현체를 선택
  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
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
