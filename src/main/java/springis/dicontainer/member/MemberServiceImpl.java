package springis.dicontainer.member;

// NOTE: 인터페이스의 구현체가 하나라면 클래스 이름을 `*Impl`로 짓는 게 관례
public class MemberServiceImpl implements MemberService {

  // NOTE: DIP 위반(구체 클래스에 의존) -> OCP 위반(구체 클래스 변경 시 코드 변경 필요)
  private final MemberRepository memberRepository = new MemoryMemberRepository();

  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }

  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }
}
