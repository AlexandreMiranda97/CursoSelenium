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
	public void testeTextField() {
		page.setNome("Alexandre");
		page.setSobrenome("Miranda da Costa");
		dsl.escreve("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.getFieldValue("elementosForm:nome"));
	}
	
	@Test
	public void doubleTextField() {
		dsl.escreve("elementosForm:nome", "Alexandre");
		Assert.assertEquals("Alexandre", dsl.getFieldValue("elementosForm:nome"));
		dsl.escreve("elementosForm:nome", "Miranda");
		Assert.assertEquals("Miranda", dsl.getFieldValue("elementosForm:nome"));
	}

	@Test
	public void deveInteragirComTextArea() {
		dsl.escreve("elementosForm:sugestoes", "teste\n\n\n\nultima linha");
		Assert.assertEquals("teste\n\n\n\nultima linha", dsl.getFieldValue("elementosForm:sugestoes"));
	}

	@Test
	public void deveInteragirComRadioButton() {
		dsl.radioClick("elementosForm:sexo:0");
		Assert.assertTrue(dsl.radioCheck("elementosForm:sexo:0"));
	}

	@Test
	public void deveInteragirComCombo() {
		dsl.selectCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.retornaValorCombo("elementosForm:escolaridade"));
	}

	@Test
	public void verificaComboMultiplo() {
		dsl.selectCombo("elementosForm:esportes", "Natação");
		dsl.selectCombo("elementosForm:esportes", "Corrida");
		dsl.selectCombo("elementosForm:esportes", "Futebol");

		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
	}

	@Test
	public void deveInteragirComBotoes() {
		dsl.clicarBotao("buttonSimple");

		WebElement botao = driver.findElement(By.id("buttonSimple"));
		Assert.assertEquals("Obridado!", botao.getAttribute("value"));
	}

	@Test
	public void deveInteragirComLinks() {
		dsl.clicarLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}

	@Test
	public void buscarTextoNaPagina() {
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}
}