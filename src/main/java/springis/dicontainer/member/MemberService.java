package springis.dicontainer.member;

// NOTE: 비즈니스 용어를 사용 (save 저장 -> join 가입, findById 아이디로 찾기 -> findMember 회원 찾기)
public interface MemberService {

  void join(Member member);

  Member findMember(Long memberId);
}
