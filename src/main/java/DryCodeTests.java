import java.util.List;

import org.junit.After;
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
	private DSL dsl;

	@Before
	public void start() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1024, 768));
		driver.manage().window().setPosition(new Point(0, 0));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	@After
	public void finish() {
		driver.quit();
	}

	@Test
	public void textFieldInteraction() {
		dsl.write("elementosForm:nome", "Alexandre Miranda");
		Assert.assertEquals("Alexandre Miranda", dsl.getFieldValue("elementosForm:nome"));
	}

	@Test
	public void textAreaInteraction() {
		dsl.write("elementosForm:sugestoes", "teste\n\n\n\nultima linha");
		Assert.assertEquals("teste\n\n\n\nultima linha", dsl.getFieldValue("elementosForm:sugestoes"));
	}

	@Test
	public void radioButtonInteraction() {
		dsl.radioClick("elementosForm:sexo:0");
		Assert.assertTrue(dsl.radioCheck("elementosForm:sexo:0"));
	}

	@Test
	public void checkBoxInteraction() {
		dsl.radioClick("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.radioCheck("elementosForm:comidaFavorita:2"));
	}

	@Test
	public void dropdownInteraction() {
		dsl.comboSelect("elementosForm:escolaridade", "Doutorado");
		Assert.assertEquals("Doutorado", dsl.getComboSelected("elementosForm:escolaridade"));
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
		dsl.comboSelect("elementosForm:esportes", "Natacao");
		dsl.comboSelect("elementosForm:esportes", "Corrida");
		dsl.comboSelect("elementosForm:esportes", "Karate");

		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());

		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
	}

	@Test
	public void buttonInteraction() {
		dsl.buttonClick("buttonSimple");

		WebElement button = driver.findElement(By.id("buttonSimple"));
		Assert.assertEquals("Obrigado!", button.getAttribute("value"));
	}

	@Test
	public void backLink() {
		dsl.linkClick("Voltar");
		Assert.assertEquals("Voltou!", dsl.getText("resultado"));
	}

	@Test
	public void pageTextFind() {
		Assert.assertEquals("Campo de Treinamento", dsl.getText(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.getText(By.className("facilAchar")));
	}
}
