package springis.dicontainer.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// NOTE: 인터페이스의 구현체가 하나라면 클래스 이름을 `*Impl`로 짓는 게 관례
// NOTE: (추상화에만 의존 => DIP 준수) -> OCP 준수
// NOTE: 사용 영역은 로직에만 집중 => 아키텍처 수준에서는 SoC, 클래스 레벨에서는 SRP 준수

// NOTE: @Service - 아무 부가 기능 없음
@Getter
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  // NOTE: <필드 주입> 컨테이너에 의존적 => 순수한 테스트 불가 (@SpringBootTest 같은 것만 가능)
  // NOTE: 테스트나 Configuration 파일을 제외하고는 사용 금지
//  @Autowired
//  private MemberRepository memberRepository;

  // NOTE: <Setter 주입> 변경 가능하고, 선택적인 의존 관계 주입
//  @Autowired
//  public void setMemberRepository(MemberRepository memberRepository) {
//    this.memberRepository = memberRepository;
//  }

  // NOTE: <생성자 주입> 생성자를 호출하는 외부에서 구현체를 선택
  // NOTE: 불변, 필수 의존 관계 주입
  // NOTE: 생성자가 1개면 @Autowired 생략 가능
//  public MemberServiceImpl(MemberRepository memberRepository) {
//    this.memberRepository = memberRepository;
//  }

  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }

  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }
}
