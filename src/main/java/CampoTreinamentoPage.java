import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {

	private DSL dsl;
	
	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new DSL(driver);
	}

	public void setName(String name) {
		dsl.write("elementosForm:nome", name);
	}
	
	public void setSurname(String surname) {
		dsl.write("elementosForm:sobrenome", surname);
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
		dsl.comboSelect("elementosForm:escolaridade", value);
	}
	
	public void setSport(String value) {
		dsl.comboSelect("elementosForm:esportes", value);
	}
	
	public void setSuggestions(String value) {
		dsl.write("elementosForm:sugestoes", value);
	}
	
	public void register() {
		dsl.buttonClick("elementosForm:cadastrar");
	}
	
	public String getRegResult() {
		return dsl.getText("resultado");
	}
	
	public String getRegName() {
		return dsl.getText("descNome");
	}
	
	public String getRegSurname() {
		return dsl.getText("descSobrenome");
	}
	
	public String getRegGender() {
		return dsl.getText("descSexo");
	}
	
	public String getRegFood() {
		return dsl.getText("descComida");
	}
	
	public String getRegGraduation() {
		return dsl.getText("descEscolaridade");
	}
	
}
