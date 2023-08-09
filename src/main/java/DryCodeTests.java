import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DryCodeTests {

	private WebDriver driver;

	@Before
	public void start() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1024, 768));
		driver.manage().window().setPosition(new Point(0, 0));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}

	@Test
	public void textFieldInteraction() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	}

	@Test
	public void textAreaInteraction() {
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste\n\n\n\nultima linha");
		Assert.assertEquals("teste\n\n\n\nultima linha", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
	}

	@Test
	public void radioButtonInteraction() {
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
	}

	@Test
	public void checkBoxInteraction() {
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
	}

	@Test
	public void dropdownInteraction() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
//		combo.selectByIndex(2);
//		combo.selectByValue("superior");
		combo.selectByVisibleText("Doutorado");
		Assert.assertEquals("Doutorado", combo.getFirstSelectedOption().getText());
	}

	@Test
	public void verifyValues() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());

		boolean encontrou = false;
		for (WebElement option : options) {
			if (option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
	}

	@Test
	public void multipleSelections() {
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("Karate");

		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());

		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
	}

	@Test
	public void buttonInteraction() {
		WebElement button = driver.findElement(By.id("buttonSimple"));
		button.click();
		Assert.assertEquals("Obrigado!", button.getAttribute("value"));
	}

	@Test
	public void backLink() {
		driver.findElement(By.linkText("Voltar")).click();
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
	}

	@Test
	public void pageTextFind() {
		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
				driver.findElement(By.className("facilAchar")).getText());
	}
}
