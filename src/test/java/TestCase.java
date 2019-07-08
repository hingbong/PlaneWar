import java.io.IOException;
import java.net.URL;
import org.junit.jupiter.api.Test;

class TestCase {

  @Test
  void test() throws IOException {
    URL resource = getClass().getClassLoader().getResource("airplane0.png");
    System.out.println(resource);
  }
}
