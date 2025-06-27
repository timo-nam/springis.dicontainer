package springis.dicontainer.scan;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class OptionalFieldTest {

  @Test
  void testOptionalField() {
    new AnnotationConfigApplicationContext(OptionalField.class);
  }
}