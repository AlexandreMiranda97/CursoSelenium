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

public class TesteCampoTreinamento {

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
		dsl.write("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.getFieldValue("elementosForm:nome"));
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
		dsl.comboSelect("elementosForm:escolaridade", "Mestrado");
		Assert.assertEquals("Mestrado", dsl.comboCheck("elementosForm:escolaridade"));
	}

	@Test
	public void verifyValues() {
		Assert.assertEquals(8, dsl.comboCheckValues("elementosForm:escolaridade"));
	}

	@Test
	public void multipleSelections() {
		dsl.comboSelect("elementosForm:esportes", "Natacao");
		dsl.comboSelect("elementosForm:esportes", "Corrida");
		dsl.comboSelect("elementosForm:esportes", "Karate");
		Assert.assertEquals(3, dsl.comboCheckValues("elementosForm:esportes"));

		dsl.comboUnselect("elementosForm:esportes", "Corrida");
		Assert.assertEquals(2, dsl.comboCheckValues("elementosForm:esportes"));
	}

	@Test
	public void buttonInteraction() {
		dsl.buttonClick("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.getElementValue("buttonSimple"));
	}

	@Test
	public void backLink() {
		dsl.linkClick("Voltar");
		Assert.assertEquals("Voltou!", dsl.getText("resultado"));
	}

	@Test
	public void pageTextFind() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1024, 768));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
				driver.findElement(By.className("facilAchar")).getText());
		driver.quit();
	}

	@Test
	public void alert() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1024, 768));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
}
