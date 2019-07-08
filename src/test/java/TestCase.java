import java.io.IOException;
import java.net.URL;
import java.util.Random;
import org.junit.jupiter.api.Test;

class TestCase {

  @Test
  void test() throws IOException {
    URL resource = getClass().getClassLoader().getResource("airplane0.png");
    System.out.println(resource);
  }

  @Test
  void test2() {
    Random random = new Random();
    for (int i = 0; i < 10; i++) {
      System.out.println(random.nextInt(2));
    }
  }
}
