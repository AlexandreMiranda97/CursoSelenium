import org.openqa.selenium.WebDriver;

public class TestAlertPage {

    private DSL dsl;

    public TestAlertPage(WebDriver driver) {
        dsl = new DSL(driver);
    }

    public String getAlertText() {
        return dsl.obterTexto("alertText");
    }

    public void clickAlertButton() {
        dsl.clicarBotao("alert");
    }

    public void clickConfirmButton() {
        dsl.clicarBotao("confirm");
    }

    public void clickPromptButton() {
        dsl.clicarBotao("prompt");
    }

    public void acceptAlert() {
        dsl.clicarBotao("alertButton");
    }

    public void dismissAlert() {
    }
}
