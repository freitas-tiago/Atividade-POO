package PetStore;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class PetStore {
	private ArrayList<Exame> exames;

	public PetStore() {
		this.exames = new ArrayList<Exame>();
	}
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public RaioX leRaioX (){

		String [] valores = new String [3];
		String [] nomeVal = {"Convênio", "Médico", "Paciente"};
		valores = leValores (nomeVal);

		int idade = this.retornaInteiro(valores[1]);

		RaioX raioX = new RaioX(valores[0],idade,valores[2]);
		return raioX;
	}

	public Sangue leSangue (){

		String [] valores = new String [3];
		String [] nomeVal = {"Convênio", "Médico", "Paciente"};
		valores = leValores (nomeVal);

		int idade = this.retornaInteiro(valores[1]);

		Sangue sangue = new Sangue(valores[0],idade,valores[2]);
		return sangue;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); // M�todo estático, que tenta tranformar uma string em inteiro
			return true;
		} catch (NumberFormatException e) { // Não conseguiu tranformar em inteiro e gera erro
			return false;
		}
	}
	public int retornaInteiro(String entrada) { // retorna um valor inteiro
		int numInt;

		//Enquanto não for possível converter o valor de entrada para inteiro, permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaExame (ArrayList<Exame> exames){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("c:\\temp\\petStore.dados"));
			for (int i = 0; i < exames.size(); i++)
				outputStream.writeObject(exames.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectOutputStream
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Exame> recuperaExame (){
		ArrayList<Exame> exameTemp = new ArrayList<Exame>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("c:\\temp\\petStore.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Exame) {
					exameTemp.add((Exame) obj);
				}   
			}          
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Exames não encontrados!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return exameTemp;
		}
	}

	public void menuPetStore (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Cadastro de exames\n" +
					"Opções:\n" +
					"1. Cadastrar Exames\n" +
					"2. Exibir Exames\n" +
					"3. Excluir Exames\n" +
					"4. Gravar Exames\n" +
					"5. Recuperar Exames\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Cadastrar exames\n" +
						"Opções:\n" +
						"1. Sangue\n" +
						"2. RaioX\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: exames.add((Exame)leSangue());
				break;
				case 2: exames.add((Exame)leRaioX());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Exame indisponível!");
				}

				break;
			case 2: // Exibir dados
				if (exames.size() == 0) {
					JOptionPane.showMessageDialog(null,"Digite o tipo de exame primeiramente");
					break;
				}
				String dados = "";
				for (int i = 0; i < exames.size(); i++)	{
					dados += exames.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: // Limpar Dados
				if (exames.size() == 0) {
					JOptionPane.showMessageDialog(null,"Digite o tipo de exame primeiramente");
					break;
				}
				exames.clear();
				JOptionPane.showMessageDialog(null,"Excluídos com sucesso!");
				break;
			case 4: // Grava Dados
				if (exames.size() == 0) {
					JOptionPane.showMessageDialog(null,"Digite o tipo de exame primeiramente");
					break;
				}
				salvaExame(exames);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: // Recupera Dados
				exames = recuperaExame();
				if (exames.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo Exames Clínicos");
				break;
			}
		} while (opc1 != 9);
	}


	public static void main (String [] args){

		PetStore pet = new PetStore ();
		pet.menuPetStore();

	}

}
