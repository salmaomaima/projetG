package mon.pfe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import mon.pfe.MonPfeApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonPfeApplication.class)
@WebAppConfiguration
public class MonPfeApplicationTests {

	@Test
	public void contextLoads() {
	}

}
