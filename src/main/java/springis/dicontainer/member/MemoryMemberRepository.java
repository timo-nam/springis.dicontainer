package springis.dicontainer.member;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

// NOTE: @Repository - 데이터 계층의 예외를 스프링 예외로 변환
@Repository
public class MemoryMemberRepository implements MemberRepository {

  // NOTE: 동시성 이슈 때문에 ConcurrentHashMap으로 구현
  private static final Map<Long, Member> store = new ConcurrentHashMap<>();

//  public MemoryMemberRepository() {
//    System.out.println("MemoryMemberRepository.MemoryMemberRepository");
//  }

  @Override
  public void save(Member member) {
    store.put(member.getId(), member);
  }

  @Override
  public Member findById(Long memberId) {
    return store.get(memberId);
  }
}
