import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class TesteGoogle {
	
	@Test
	public void Teste () {
		WebDriver driver = new ChromeDriver();
//		WebDriver driverFirefox = new FirefoxDriver();
//		WebDriver driverEdge = new EdgeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("http://www.google.com");
//		driverFirefox.get("http://www.google.com");
//		driverEdge.get("http://www.google.com");
		Assert.assertEquals ("Google", driver.getTitle());
//		Assert.assertEquals ("Google", driverFirefox.getTitle());
//		Assert.assertEquals ("Google", driverEdge.getTitle());
//		driver.close();
		driver.quit();
	}

}
