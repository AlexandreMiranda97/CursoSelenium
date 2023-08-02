import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestAlert {

	@Test
	public void simpleAlertInteract() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1024, 768));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		Assert.assertEquals("Alert Simples", alert.getText());
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(alertText);
		driver.quit();
	}

	@Test
	public void alertConfirmRefuse() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1024, 768));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

//		Alert acceptance and message validation
		Alert alert = driver.switchTo().alert();
		driver.findElement(By.id("confirm")).click();
		Assert.assertEquals("Confirm Simples", alert.getText());
		alert.accept();
		Assert.assertEquals("Confirmado", alert.getText());
		alert.accept();

//		Alert denial and message validation
		driver.findElement(By.id("confirm")).click();
		Assert.assertEquals("Confirm Simples", alert.getText());
		alert.dismiss();
		Assert.assertEquals("Negado", alert.getText());
		alert.accept();
		driver.quit();
	}

	@Test
	public void alertPrompt() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1024, 768));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("prompt")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alert.getText());
		alert.sendKeys("12");
		alert.accept();
		Assert.assertEquals("Era 12?", alert.getText());
		alert.accept();
		Assert.assertEquals(":D", alert.getText());
		alert.accept();
		
		driver.quit();		
	}
	
	@Test
	public void fullfilledForm() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1024, 768));
		driver.manage().window().setPosition(new Point(0, 0));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Alexandre");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Miranda da Costa");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("2o grau completo");
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Natacao");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Lorem Ipsum Lorem Ipsum Lorem Ipsum");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Alexandre"));
		Assert.assertTrue(driver.findElement(By.id("descSobrenome")).getText().endsWith("Miranda da Costa"));
		Assert.assertTrue(driver.findElement(By.id("descSexo")).getText().endsWith("Masculino"));
		Assert.assertEquals("Comida: Pizza", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: 2graucomp", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Natacao", driver.findElement(By.id("descEsportes")).getText());
		Assert.assertEquals("Sugestoes: Lorem Ipsum Lorem Ipsum Lorem Ipsum", driver.findElement(By.id("descSugestoes")).getText());
		
		driver.quit();
	}
}
