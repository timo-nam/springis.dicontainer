package springis.dicontainer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// NOTE: @Configuration - CGLIB 활용 싱글톤 빈 관리
@Configuration
// NOTE: 이름 그대로 @Component 붙은 걸 다 스캔
@ComponentScan(
    // NOTE: basePackages나 basePackageClasses로 탐색 시작 위치를 명시하지 않으면, @ComponentScan이 붙은 클래스가 속한 패키지가 기본값
    basePackages = "springis.dicontainer",
    excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

  // NOTE: 빈 이름 충돌(수동 vs 자동) => 수동 우선 => Overriding bean definition for bean
  // NOTE: 스프링 부트에서는 수동-자동 충돌 시에도 오버라이드 하지 않고 오류 발생 => BeanDefinitionOverrideException
//  @Bean
//  public MemberRepository memoryMemberRepository() {
//    return new MemoryMemberRepository();
//  }
}

// NOTE: 빈 이름 충돌(자동 vs 자동) => ConflictingBeanDefinitionException
//@Component
//class OrderServiceImpl {
//
//}