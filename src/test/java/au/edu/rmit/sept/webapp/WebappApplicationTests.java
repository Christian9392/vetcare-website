package au.edu.rmit.sept.webapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@SpringBootTest
class WebappApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void seleniumBaseTest() {
		// Initializing a ChromeDriver, downloads if necessary.
		WebDriver driver = new ChromeDriver();

		// Applied wait time if testing element does not exist
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//maximize window
		driver.manage().window().maximize();

		//open browser with desried URL
		driver.get("localhost:8080");
	}

}
