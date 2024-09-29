package au.edu.rmit.sept.webapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class WebappApplicationTests {

//	@Test
//	void contextLoads() {
//	}
//
//	@Test
//	void emulateLargeScreen() {
//		WebDriver driver = new ChromeDriver();
//
//		// Change screen size to desktop 1080p
//		driver.manage().window().setSize(new Dimension(1920, 1080));
//
//		driver.get("http://localhost:8080");
//	}
//
//	@Test
//	void emulateMobileScreen() {
//		// Set mobile emulation
//		Map<String, String> mobileEmulation = new HashMap<>();
//		mobileEmulation.put("deviceName", "Nexus 5");
//
//		// Apply options to new web driver
//		ChromeOptions chromeOptions = new ChromeOptions();
//		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
//		WebDriver driverMobile = new ChromeDriver(chromeOptions);
//
//		driverMobile.get("http://localhost:8080");
//	}
//
//	@Test
//	void seleniumBrowsers() {
//		// Create webdrivers for main browsers
//		WebDriver driverChrome = new ChromeDriver();
//		WebDriver driverFirefox = new FirefoxDriver();
//		WebDriver driverEdge = new EdgeDriver();
//
//		driverChrome.get("http://localhost:8080");
//		driverFirefox.get("http://localhost:8080");
//		driverEdge.get("http://localhost:8080");
//	}
 }
