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
	public void formRegistration() {
		dsl.write("elementosForm:nome", "Alexandre");
		dsl.write("elementosForm:sobrenome", "Miranda da Costa");
		dsl.radioClick("elementosForm:sexo:0");
		dsl.radioClick("elementosForm:comidaFavorita:2");
		dsl.comboSelect("elementosForm:escolaridade", "Doutorado");
		dsl.comboSelect("elementosForm:esportes", "Natacao");
		dsl.write("elementosForm:sugestoes", "Lorem Ipsum Lorem Ipsum Lorem Ipsum");

		dsl.buttonClick("elementosForm:cadastrar");

		Assert.assertTrue(dsl.checkText("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.checkText("descNome").endsWith("Alexandre"));
		Assert.assertTrue(dsl.checkText("descSobrenome").endsWith("Miranda da Costa"));
		Assert.assertTrue(dsl.checkText("descSexo").endsWith("Masculino"));
		Assert.assertEquals("Comida: Pizza", dsl.checkText(By.id("descComida")));
		Assert.assertEquals("Escolaridade: doutorado", dsl.checkText(By.id("descEscolaridade")));
		Assert.assertEquals("Esportes: Natacao", dsl.checkText("descEsportes"));
		Assert.assertEquals("Sugestoes: Lorem Ipsum Lorem Ipsum Lorem Ipsum", dsl.checkText("descSugestoes"));
	}
}
