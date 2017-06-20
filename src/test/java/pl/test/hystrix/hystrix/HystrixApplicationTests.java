package pl.test.hystrix.hystrix;

import java.util.Random;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HystrixApplicationTests {

	@Autowired
	private DaoComponent daoComponent;

	@Test
	public void contextLoads() throws InterruptedException {
		while (true) {
			final int sleepTime = new Random().nextInt(50);
			Thread.sleep(sleepTime);
			this.executeCallingThread();
		}
	}

	private void executeCallingThread() {
		new Thread(() -> daoComponent.getDatabaseResponse(UUID.randomUUID().toString())).start();
	}

}
