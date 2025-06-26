package springis.dicontainer.singleton;

public class SingletonService {

  // NOTE: 클래스 수준 싱글톤은 안티 패턴
  // NOTE: 구체 클래스에 의존할 수밖에 없음 -> DIP 위반 -> OCP 위반
  // NOTE: Side effect 때문에 테스트가 어려움
  private static final SingletonService instance = new SingletonService();

  public static SingletonService getInstance() {
    return instance;
  }

  private SingletonService() {
  }

  public void logic() {
    System.out.println("로직 실행");
  }
}
