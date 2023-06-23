package PetStore;

public class Sangue extends Exame {

	private static final long serialVersionUID = 1L;

	public String soar() {
		return "Fazer jejum 6h, remédios e bebidas proibidos.";
	}
	public Sangue(String convenio, int medico, String paciente) {
		super(convenio, medico, paciente);
		this.especie = "Preparação Própria";
	}
}
