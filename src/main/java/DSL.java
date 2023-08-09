import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	private WebDriver driver;
	
	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	public void write(String id_campo, String text) {
		driver.findElement(By.id(id_campo)).sendKeys(text);
	}
	
	public String getFieldValue(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	
	public void radioClick(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public boolean radioCheck(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void comboSelect(String id, String value) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(value);
	}
	
	public String comboCheck (String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public void buttonClick(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public void linkClick(String link) {
		driver.findElement(By.linkText(link)).click();
	}
	
	public String checkText(By by) {
		return driver.findElement(by).getText();
	}
	
	public String checkText(String id) {
		return checkText(By.id(id));
	}
}
