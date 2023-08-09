import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBusinessRules {

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
	public void nameMandatory() {
		dsl.buttonClick("elementosForm:cadastrar");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		alert.accept();
	}

	@Test
	public void surnameMandatory() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Alexandre");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		alert.accept();
	}

	@Test
	public void genderMandatory() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Alexandre");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Miranda");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		alert.accept();
	}

	@Test
	public void ambiguousFood() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Alexandre");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Miranda");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		alert.accept();	
	}
	
	@Test
	public void ambiguousSport() {
		dsl.write("elementosForm:nome", "Alexandre");
		dsl.write("elementosForm:sobrenome", "Miranda da Costa");
		dsl.buttonClick("elementosForm:sexo:0");
		dsl.buttonClick("elementosForm:comidaFavorita:0");
		dsl.comboSelect("elementosForm:esportes", "Natacao");
		dsl.comboSelect("elementosForm:esportes", "O que eh esporte?");
		dsl.buttonClick("elementosForm:cadastrar");
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		alert.accept();
	}
}
