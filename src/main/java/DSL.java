import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	private WebDriver driver;

	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	/********************** TextField & TextArea **********************/

	public void write(By by, String text) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(text);
	}

	public void write(String id_campo, String text) {
		write(By.id(id_campo), text);
	}

	public String getFieldValue(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}

	/********************** Radio & Check **********************/

	public void radioClick(String id) {
		driver.findElement(By.id(id)).click();
	}

	public boolean radioCheck(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}

	public void checkboxClick(String id) {
		driver.findElement(By.id(id)).click();
	}

	public boolean checkboxCheck(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}

	/********************** Combo **********************/

	public void comboSelect(String id, String value) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(value);
	}

	public void comboUnselect(String id, String value) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(value);
	}

	public String getComboSelected(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}

	public List<String> getComboValues(String id) {
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> values = new ArrayList<String>();
		for (WebElement option : allSelectedOptions) {
			values.add(option.getText());
		}
		return values;
	}

	public int getComboQuantity(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}

	public boolean comboOptionVerify(String id, String opcao) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for (WebElement option : options) {
			if (option.getText().equals(opcao)) {
				return true;
			}
		}
		return false;
	}

	/********************** Buttons **********************/

	public void buttonClick(String id) {
		driver.findElement(By.id(id)).click();
	}

	public String getElementValue(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");
	}

	/********************** Link **********************/

	public void linkClick(String link) {
		driver.findElement(By.linkText(link)).click();
	}

	/********************** Text **********************/

	public String getText(By by) {
		return driver.findElement(by).getText();
	}

	public String getText(String id) {
		return getText(By.id(id));
	}

	/********************** Alerts **********************/

	public String alertGetText() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	public String alertGetTextAccept() {
		Alert alert = driver.switchTo().alert();
		String value = alert.getText();
		alert.accept();
		return value;
	}

	public String alertGetTextDismiss() {
		Alert alert = driver.switchTo().alert();
		String value = alert.getText();
		alert.dismiss();
		return value;
	}

	public void alertWrite(String value) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(value);
		alert.accept();
	}

	/********************** Frames and Windows **********************/

	public void enterFrame(String id) {
		driver.switchTo().frame(id);
	}

	public void exitFrame(String id) {
		driver.switchTo().defaultContent();
	}

	public void changeWindow(String id) {
		driver.switchTo().window(id);
	}
}
