import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {

    private DSL dsl;

    public CampoTreinamentoPage(WebDriver driver) {
        dsl = new DSL(driver);
    }

    public void setName(String name) {
        dsl.escreve("elementosForm:nome", name);
    }

    public void setSurname(String surname) {
        dsl.escreve("elementosForm:sobrenome", surname);
    }

    public void setMaleGender() {
        dsl.radioClick("elementosForm:sexo:0");
    }

    public void setFemaleGender() {
        dsl.radioClick("elementosForm:sexo:1");
    }

    public void setFoodPizza() {
        dsl.radioClick("elementosForm:comidaFavorita:2");
    }

    public void setGraduation(String value) {
        dsl.selectCombo("elementosForm:escolaridade", value);
    }

    public void setSport(String value) {
        dsl.selectCombo("elementosForm:esportes", value);
    }

    public void setSuggestions(String value) {
        dsl.escreve("elementosForm:sugestoes", value);
    }

    public void register() {
        dsl.clicarBotao("elementosForm:cadastrar");
    }

    public String getRegResult() {
        return dsl.obterTexto("resultado");
    }

    public String getRegName() {
        return dsl.obterTexto("descNome");
    }

    public String getRegSurname() {
        return dsl.obterTexto("descSobrenome");
    }

    public String getRegGender() {
        return dsl.obterTexto("descSexo");
    }

    public String getRegFood() {
        return dsl.obterTexto("descComida");
    }

    public String getRegGraduation() {
        return dsl.obterTexto("descEscolaridade");
    }

}
