package PetStore;

public class RaioX extends Exame {

	private static final long serialVersionUID = 1L;

	public String soar() {
		return "Importante Jejuar e tomar laxantes";
	}
	public RaioX(String convenio, int medico, String paciente) {
		super(convenio, medico, paciente);
		this.especie = "Preparação Própria";
	}
}
