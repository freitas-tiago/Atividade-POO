package PetStore;

public class Ecografia extends Exame {

	private static final long serialVersionUID = 1L;

	public String soar() {
		return "Importante Jejuar e tomar bastante água";
	}
	public Ecografia(String convenio, int medico, String paciente) {
		super(convenio, medico, paciente);
		this.especie = "Preparação Própria";
	}
}
