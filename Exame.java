package PetStore;

import java.io.Serializable;

public abstract class Exame implements Serializable {

	private static final long serialVersionUID = 1L;
	private   String convenio;
	private   int medico;
	private   String paciente;
	protected String especie;
	
	public Exame(String convenio, int medico, String paciente) {
		this.convenio = convenio;
		this.medico = medico;
		this.paciente = paciente;
	}
	public String toString() {
		String retorno = "";
		retorno += "Convênio: "     + this.convenio     + "\n";
		retorno += "Médico: "    + this.medico    + "\n";
		retorno += "Paciente: "     + this.paciente     + "\n";
		retorno += "Tipo: "  + this.especie  + "\n";
		retorno += "Orientações: "  + soar()        + "\n";
		return retorno;
	}
	public abstract String soar();
}
