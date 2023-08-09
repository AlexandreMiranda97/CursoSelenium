import java.util.Arrays;
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
	private CampoTreinamentoPage page;

	@Before
	public void start() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1024, 768));
		driver.manage().window().setPosition(new Point(0, 0));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}

	@After
	public void finish() {
		driver.quit();
	}

	@Test
	public void textFieldInteraction() {
		page.setName("Alexandre");
		page.setSurname("Miranda da Costa");
		dsl.write("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.getFieldValue("elementosForm:nome"));
	}
	
	@Test
	public void doubleTextField() {
		dsl.write("elementosForm:nome", "Alexandre");
		Assert.assertEquals("Alexandre", dsl.getFieldValue("elementosForm:nome"));
		dsl.write("elementosForm:nome", "Miranda");
		Assert.assertEquals("Miranda", dsl.getFieldValue("elementosForm:nome"));
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
		Assert.assertEquals("Mestrado", dsl.getComboSelected("elementosForm:escolaridade"));
	}

	@Test
	public void verifyValues() {
		Assert.assertEquals(8, dsl.getComboQuantity("elementosForm:escolaridade"));
	}

	@Test
	public void multipleSelections() {
		dsl.comboSelect("elementosForm:esportes", "Natacao");
		dsl.comboSelect("elementosForm:esportes", "Corrida");
		dsl.comboSelect("elementosForm:esportes", "O que eh esporte?");
		
		List<String> opcoesMarcadas = dsl.getComboValues("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.comboUnselect("elementosForm:esportes", "O que eh esporte?");
		opcoesMarcadas = dsl.getComboValues("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "Corrida")));
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
		Assert.assertEquals("Campo de Treinamento", dsl.getText(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.getText(By.className("facilAchar")));
	}
}
