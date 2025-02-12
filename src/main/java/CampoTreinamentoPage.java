import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {

    private DSL dsl;

    public CampoTreinamentoPage(WebDriver driver) {
        dsl = new DSL(driver);
    }

    public void setNome(String name) {
        dsl.escreve("elementosForm:nome", name);
    }

    public void setSobrenome(String surname) {
        dsl.escreve("elementosForm:sobrenome", surname);
    }

    public void setMasculino() {
        dsl.radioClick("elementosForm:sexo:0");
    }

    public void setFeminino() {
        dsl.radioClick("elementosForm:sexo:1");
    }

    public void setComidaPizza() {
        dsl.radioClick("elementosForm:comidaFavorita:2");
    }

    public void setGraduacao(String value) {
        dsl.selectCombo("elementosForm:escolaridade", value);
    }

    public void setEsporte(String value) {
        dsl.selectCombo("elementosForm:esportes", value);
    }

    public void setSugestao(String value) {
        dsl.escreve("elementosForm:sugestoes", value);
    }

    public void registrar() {
        dsl.clicarBotao("elementosForm:cadastrar");
    }

    public String getRegResult() {
        return dsl.obterTexto("resultado");
    }

    public String getRegNome() {
        return dsl.obterTexto("descNome");
    }

    public String getRegSobrenome() {
        return dsl.obterTexto("descSobrenome");
    }

    public String getRegGenero() {
        return dsl.obterTexto("descSexo");
    }

    public String getRegComida() {
        return dsl.obterTexto("descComida");
    }

    public String getRegGraduacao() {
        return dsl.obterTexto("descEscolaridade");
    }

    public String getRegSugestao() {
        return dsl.obterTexto("descSugestoes");
    }

}
