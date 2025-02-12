import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class TesteCadastro {
	
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
	public void cadastroComSucesso() {
		page.setNome("Alexandre");
		page.setSobrenome("Miranda da Costa");
		page.setMasculino();
		page.setComdidaFavorita("Carne");
		page.setComdidaFavorita("Pizza");
		page.setGraduacao("Doutorado");
		page.setEsporte("Natacao");
		page.setSugestao("Lorem Ipsum Lorem Ipsum Lorem Ipsum");
		page.registrar();

		dsl.clicarBotao("elementosForm:cadastrar");

		Assert.assertTrue(page.getRegResult().startsWith("Cadastrado!"));
		Assert.assertTrue(page.getRegNome().endsWith("Alexandre"));
		Assert.assertEquals("Sobrenome: Miranda da Costa", page.getRegSobrenome());
		Assert.assertEquals("Sexo: Masculino", page.getRegGenero());
		Assert.assertEquals("Comida: Carne Pizza", page.getRegComida());
		Assert.assertEquals("Escolaridade: doutorado", page.getRegGraduacao());
		Assert.assertEquals("Esportes: Natacao", dsl.obterTexto("descEsportes"));
		Assert.assertEquals("Sugestoes: Lorem Ipsum Lorem Ipsum Lorem Ipsum", page.getRegSugestao());
	}
}