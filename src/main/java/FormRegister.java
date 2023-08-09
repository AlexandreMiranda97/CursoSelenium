import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FormRegister {
	
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
	public void formRegistration() {
		page.setName("Alexandre");
		page.setSurname("Miranda da Costa");
		page.setMaleGender();
		page.setFoodPizza();
		page.setGraduation("Doutorado");
		page.setSport("Natacao");
		page.register();

		dsl.buttonClick("elementosForm:cadastrar");

		Assert.assertTrue(page.getRegResult().startsWith("Cadastrado!"));
		Assert.assertTrue(page.getRegName().endsWith("Alexandre"));
		Assert.assertEquals("Sobrenome: Miranda da Costa", page.getRegSurname());
		Assert.assertEquals("Sexo: Masculino", page.getRegGender());
		Assert.assertEquals("Comida: Pizza", dsl.getText("descComida"));
		Assert.assertEquals("Escolaridade: doutorado", dsl.getText("descEscolaridade"));
		Assert.assertEquals("Esportes: Natacao", dsl.getText("descEsportes"));
		Assert.assertEquals("Sugestoes: Lorem Ipsum Lorem Ipsum Lorem Ipsum", dsl.getText("descSugestoes"));
	}
}
